package com.obizsoft.pegasusframework.common;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		
		add(new Link("logoutLink"){

			@Override
			public void onClick() {
				AuthenticatedWebSession.get().invalidate();
	            setResponsePage(getApplication().getHomePage());
			}
			
		}.add(new Label("logout", "<i class=\"icon-key\"></i> Log Out").
				setEscapeModelStrings(false)));
		
		add(new Label("username", ((PegasusAuthenticatedWebSession)AuthenticatedWebSession.get()).getUserName()));
	}

}
