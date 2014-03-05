package com.obizsoft.pegasusframework.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;


public abstract class BasePage extends WebPage implements MenuPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static MenuItem DEMO =
			 new MenuItem(Model.of("DEMO"), Model.of("CORE::DEMO"), DemoPage.class);
		
	protected static MenuItem ROOT = new MenuItem(null, Model.of("CORE"), DEMO);
	
	private Component headerPanel;
	
	private static class MenuModel
		 extends LoadableDetachableModel
			 <List<? extends MenuItem>> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			protected List<? extends MenuItem> load() {
				 List<MenuItem> items=new ArrayList<MenuItem>();
				 
				 for(MenuItem item : ROOT.getChildren()){
					 items.add(item);
				 }
				 return items;
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
