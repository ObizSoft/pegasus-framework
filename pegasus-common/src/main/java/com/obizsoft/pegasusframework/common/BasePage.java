package com.obizsoft.pegasusframework.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.LoadableDetachableModel;


public abstract class BasePage extends WebPage implements MenuPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Component headerPanel;
	
	private static class MenuModel
		 extends LoadableDetachableModel
			 <MenuItem> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			protected MenuItem load() {
//				 List<MenuItem> items = new ArrayList<MenuItem>();
//				 
////				 for(MenuItem item : ModuleRegistry.getRootMenu().getChildren()){
////					 items.add(item);
////				 }
//				 items.add(ModuleRegistry.getRootMenu());
//				 return items;
				return ModuleRegistry.getRootMenu();
			 }
	}
	

    public BasePage(){
		add(setHeaderPanel(new HeaderPanel("headerPanel")));
		
		add(new Menu("menu", new MenuModel()));
	}

	public Component getHeaderPanel() {
		return headerPanel;
	}

	public Component setHeaderPanel(Component headerPanel) {
		this.headerPanel = headerPanel;
		return headerPanel;
	}

	

}
