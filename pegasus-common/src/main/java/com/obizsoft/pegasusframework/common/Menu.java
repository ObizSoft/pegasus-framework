package com.obizsoft.pegasusframework.common;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu extends WebComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(Menu.class);

	public Menu(String id, IModel<MenuItem> item) {
		super(id, item);
	}

	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		StringBuilder markup = new StringBuilder();
		renderMenu(markup);
		replaceComponentTagBody(markupStream, openTag, markup);
	}

	private void renderMenu(StringBuilder markup) {
		MenuItem item = (MenuItem) getDefaultModelObject();

		MenuItem current = null;
		if (getPage() instanceof MenuPage) {
			current = ((MenuPage) getPage()).getItem();
		}

		Set<MenuItem> selected = new HashSet<MenuItem>();
		while (current != null) {
			selected.add(current);
			current = current.getParent();
		}
		renderItem(markup, item, selected);
		
		// markup.append("<ul id=\"nav\">");
		//
		// for (MenuItem item : items) {
		// CharSequence url = "#";
		// if(item.getPage() != null){
		// url = urlFor(item.getPage(), null);
		// }
		//
		// String label =
		// Strings.escapeMarkup(item.getLabel().getObject()).toString();
		// item.getLabel().detach();
		//
		// markup.append("<li ");
		// if (item.equals(current)) {
		// markup.append(" class='current'");
		// }
		// markup.append(">");
		// markup.append("<a ");
		// markup.append("href='").append(url).append("'>");
		// markup.append("<i class=\"icon-table\"></i>");
		// markup.append(label);
		// markup.append("</a>");
		// markup.append("</li>");
		// }
		// markup.append("</ul>");

	}

	private void renderItem(StringBuilder markup, MenuItem item,
			Set<MenuItem> selected) {
		
		
		if (item.getParent() == null) {
			markup.append("<ul id=\"nav\">");
		} else {
			markup.append("<ul class=\"sub-menu\" ");
			if (selected.contains(item)){
				markup.append(" style=\"display: block;\" ");
			}
			markup.append(">");
		}
		
		for (MenuItem child : item.getChildren()) {
			CharSequence url = "#";
			if (child.getPage() != null) {
				url = urlFor(child.getPage(), null);
			}

			String label = Strings.escapeMarkup(child.getLabel().getObject())
					.toString();
			child.getLabel().detach();
			
			logger.debug("====> Item : " + label
					+ " Parent == " + item.getParent());
			
			markup.append("<li ");
			if (selected.contains(child)) {
				if(child.getChildren().size() > 0){
					markup.append(" class='current open'");
				}else{
					markup.append(" class='current'");
				}
			}
			
			markup.append(">");
			markup.append("<a ");
			markup.append("href='").append(url).append("'>");
			markup.append("<i class=\"icon-angle-right\"></i>");
			
			markup.append(label);
			
			if(child.getChildren().size() > 0){
				if (selected.contains(child)) {
					markup.append("<i class=\"arrow icon-angle-down\"></i>");
				}else{
					markup.append("<i class=\"arrow icon-angle-left\"></i>");
				}
			}
			
			
			
			markup.append("</a>");

			// sample code renders the anchor tag for the menu item
			//selected.contains(child) && 
			if (child.getChildren().size() > 0) {
				
				renderItem(markup, child, selected);
			}

			markup.append("</li>");
		}
		markup.append("</ul>");
	}
}
