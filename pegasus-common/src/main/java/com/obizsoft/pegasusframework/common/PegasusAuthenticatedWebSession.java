package com.obizsoft.pegasusframework.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class PegasusAuthenticatedWebSession extends AuthenticatedWebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(PegasusAuthenticatedWebSession.class);

	private Map<String, String> userSettings = new HashMap<String, String>();
	
	public void addSetting(String key, String value){
		userSettings.put(key, value);
	}
	public String getSetting(String key){
		return userSettings.get(key);
	}
	
	@SpringBean(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	public PegasusAuthenticatedWebSession(Request request) {
		super(request);
		injectDependencies();
		ensureDependenciesNotNull();
	}

	private void ensureDependenciesNotNull() {
		if (authenticationManager == null) {
			throw new IllegalStateException(
					"An authenticationManager is required.");
		}
	}

	private void injectDependencies() {
		Injector.get().inject(this);
	}

	@Override
	public boolean authenticate(final String username, final String password) {
		boolean authenticated = false;
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			authenticated = authentication.isAuthenticated();
		} catch (AuthenticationException e) {
			logger.warn(String.format(
					"User &#039;%s&#039; failed to login. Reason: %s",
					username, e.getMessage()));
			authenticated = false;
		}
		return authenticated;
	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();
		getRolesIfSignedIn(roles);
		return roles;
	}

	private void getRolesIfSignedIn(Roles roles) {
		if (isSignedIn()) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			logger.debug("Get Authentication : " + authentication);
			addRolesFromAuthentication(roles, authentication);
		}
	}

	private void addRolesFromAuthentication(Roles roles,
			Authentication authentication) {
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
	}
	
	public String getUserName(){
		logger.debug("Is Signed In ? " + isSignedIn());
		if (isSignedIn()) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			return authentication.getName();
		}
		return null;
	}
}
