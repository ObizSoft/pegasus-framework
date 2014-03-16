package com.obizsoft.pegasusframework.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuItem implements Serializable {
	/**
	 * 
	 */
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	private final IModel<String> key;
	private final IModel<String> label;
	private final Class<? extends Page> page;
	private MenuItem parent;
	private List<MenuItem> children = new ArrayList<MenuItem>();

	public IModel<String> getLabel() {
		return label;
	}

	public Class<? extends Page> getPage() {
		return page;
	}



	public MenuItem getParent() {
		return this.parent;
	}
	
	public List<MenuItem> getChildren() {
		return children;
	}
	public void addChild(MenuItem child){
		if(this.children == null){
			this.children = new ArrayList<MenuItem>();
		}
		this.children.add(child);
		child.parent = this;
	}

	public MenuItem(IModel<String> label, IModel<String> key, MenuItem... children) {
		this(label, key, null, children);
	}

	public MenuItem(IModel<String> label, IModel<String> key, Class<? extends Page> page) {
		this(label, key, page, new MenuItem[0]);
	}

	
	private MenuItem(IModel<String> label, IModel<String> key, Class<? extends Page> page,
			MenuItem... children) {
		this.label = label;
		this.page = page;
		this.key = key;
		
		if(children != null){
			
			for (MenuItem child : children) {
				logger.debug("======> set parent of " + child  + " == " + this);
				this.addChild(child);
				
			}
		}
		
	}
	
}