package com.obizsoft.pegasusframework.common;

import java.io.Serializable;

import org.apache.wicket.model.Model;

public class ModuleRegistry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MenuItem ROOT = new MenuItem(null, Model.of("ROOT"));
	
	public static MenuItem getRootMenu(){
		return ROOT;
	}
	public static void addModuleMenu(MenuItem menu){
		ROOT.addChild(menu);
	}
}
