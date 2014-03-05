package com.obizsoft.pegasusframework.common;



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
