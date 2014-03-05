package com.obizsoft.pegasusframework.common;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

public class MenuItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IModel<String> key;
	private final IModel<String> label;
	private final Class<? extends Page> page;

	public IModel<String> getLabel() {
		return label;
	}

	public Class<? extends Page> getPage() {
		return page;
	}

	private MenuItem parent;
	private MenuItem[] children;

	public MenuItem getParent() {
		return parent;
	}

	public MenuItem[] getChildren() {
		return children;
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
		this.children = children;
		this.key = key;
		for (MenuItem child : children) {
			child.parent = this;
		}
	}
	
}