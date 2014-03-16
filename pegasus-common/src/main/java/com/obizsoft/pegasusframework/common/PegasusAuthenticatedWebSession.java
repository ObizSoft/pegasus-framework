package com.obizsoft.pegasusframework.common;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class PegasusAuthenticatedWebSession extends AuthenticatedWebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PegasusAuthenticatedWebSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(final String username, final String password) {
		final String WICKET = "wicket";

        // Check username and password
        return WICKET.equals(username) && WICKET.equals(password);
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn())
        {
            // If the user is signed in, they have these roles
            return new Roles(Roles.ADMIN);
        }
        return null;
	}

}
