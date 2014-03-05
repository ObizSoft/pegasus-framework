package com.obizsoft.pegasusframework.common;


import org.apache.wicket.model.Model;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonModuleManager implements ModuleManager {
	
	protected static MenuItem SAMPLE =
			 new MenuItem(Model.of("Sample"), Model.of("CORE::SAMPLE"), SamplePage.class);
		
	protected static MenuItem ROOT = 
			new MenuItem(Model.of("Common"), Model.of("Common"), SAMPLE);
	

	@Override
	public List<MenuItem> getMenus() {
		return null;
	}

	@Override
	public MenuItem getRootMenu() {
		return ROOT;
	}

}
