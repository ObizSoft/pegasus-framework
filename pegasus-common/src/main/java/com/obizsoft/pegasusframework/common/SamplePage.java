package com.obizsoft.pegasusframework.common;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;


@AuthorizeInstantiation("ROLE_ADMIN")
public class SamplePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SamplePage(){
		super();
	}

	@Override
	public MenuItem getItem() {
		return CommonModuleManager.SAMPLE;
	}
	
}
