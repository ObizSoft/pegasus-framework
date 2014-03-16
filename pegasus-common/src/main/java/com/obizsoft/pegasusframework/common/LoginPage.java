package com.obizsoft.pegasusframework.common;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class LoginPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginPage(){
		this(null);
	}
	
	public LoginPage(final PageParameters parameters){
		add(new LoginPanel("loginPanel"));
	}
	
}
