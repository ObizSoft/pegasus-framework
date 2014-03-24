package com.obizsoft.pegasusframework.common;

import java.util.Map;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.obizsoft.Start#main(String[])
 */
public class PegasusWicketApplication extends AuthenticatedWebApplication {

	Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	private boolean isInitialized = false;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return SamplePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		if (!isInitialized) {
			super.init();

			/**
			 * To make sure Wicket use UTF-8 in parsing html template and
			 * generate the response we explicitly set the application settings
			 * to UTF-8 via below tow lines.
			 */
			getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
			getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

			getComponentInstantiationListeners().add(
					new SpringComponentInjector(this));

			/**
			 * Initialize Application Menus
			 */
			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(getServletContext());
			Map<String, ModuleManager> mm = applicationContext
					.getBeansOfType(ModuleManager.class);

			if (mm != null) {
				for (String key : mm.keySet()) {
					logger.debug("====> ModuleManager : " + key + " Class:"
							+ mm.get(key).toString());
					ModuleRegistry.addModuleMenu(mm.get(key).getRootMenu());
				}
			}

			isInitialized = true;
		}
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return PegasusAuthenticatedWebSession.class;
	}
}
