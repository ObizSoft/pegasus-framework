package com.obizsoft.pegasusframework.common;

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obizsoft.pegasusframework.common.MenuPage;

public class Menu extends WebComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(Menu.class);

	

//	public class SubmenuFragment extends Fragment {
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//
//		public SubmenuFragment(String id, String markupId,
//				MarkupContainer markupProvider, MenuItem model) {
//			super(id, markupId, markupProvider);
//
//			add(new ListView<MenuItem>("submenuList", model.getChildren()) {
//				@Override
//				protected void populateItem(ListItem<MenuItem> item) {
//					PageParameters pars = new PageParameters();
//					pars.add("navId", item.getModelObject().getNavId());
//
//					BookmarkablePageLink submenuActionLink = new BookmarkablePageLink(
//							"submenuActionLink", HomePage.class, pars);
//
//					item.add(submenuActionLink);
//
//					Label submenuNameLabel = new Label("submenuName",
//							new PropertyModel(item.getModel(), "name"));
//					submenuNameLabel.setRenderBodyOnly(true);
//					submenuActionLink.add(submenuNameLabel);
//					if (item.getModelObject().isCurrent()) {
//						item.add(new AttributeModifier("class", "current"));
//					}
//				}
//			});
//		}
//
//	}

	public Menu(String id, IModel<List<? extends MenuItem>> items) {
		super(id, items);

//		logger.debug(this.getPage().toString());

		//
		// add(new ListView<MenuItem>("navList", navItems) {
		// @Override
		// protected void populateItem(ListItem<MenuItem> item) {
		//
		//
		// PageParameters pars = new PageParameters();
		// pars.add("navId", item.getModelObject().getNavId());
		//
		// BookmarkablePageLink actionLink =
		// new BookmarkablePageLink("actionLink", HomePage.class, pars);
		//
		// item.add(actionLink);
		//
		// Label nameLabel = new Label("name",
		// new PropertyModel(item.getModel(), "name"));
		//
		// nameLabel.setRenderBodyOnly(true);
		// actionLink.add(nameLabel);
		// if(!item.getModelObject().hasChildren()){
		// if(item.getModelObject().isCurrent()){
		// item.add(new AttributeModifier("class", "current"));
		// }
		// Fragment fragment = new Fragment ("submenuArea", "emptySubmenuFrag",
		// this);
		// fragment.setRenderBodyOnly(true);
		// item.add(fragment);
		//
		// }else{
		// if(item.getModelObject().isCurrent()){
		// item.add(new AttributeModifier("class", "open"));
		// }
		// Fragment fragment =
		// new SubmenuFragment ("submenuArea", "submenuFrag", this,
		// item.getModelObject());
		// fragment.setRenderBodyOnly(true);
		// item.add(fragment);
		// }
		//
		// }
		// });
	}

	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		StringBuilder markup = new StringBuilder();
		renderMenu(markup);
		replaceComponentTagBody(markupStream, openTag, markup);
	}

	private void renderMenu(StringBuilder markup) {
		List<? extends MenuItem> items = (List<? extends MenuItem>) getDefaultModelObject();
		markup.append("<ul id=\"nav\">");
		MenuItem current = null;
		if (getPage() instanceof MenuPage) {
			current = ((MenuPage) getPage()).getItem();
		}
		
		for (MenuItem item : items) {
			CharSequence url = urlFor(item.getPage(), null);
			String label = Strings.escapeMarkup(item.getLabel().getObject()).toString();
			item.getLabel().detach();
			
			markup.append("<li ");
			if (item.equals(current)) {
				markup.append(" class='current'");
			}
			markup.append(">");
			markup.append("<a ");
			markup.append("href='").append(url).append("'>");
			markup.append("<i class=\"icon-table\"></i>");
			markup.append(label);
			markup.append("</a>");
			markup.append("</li>");
		}
		markup.append("</ul>");
		
	}

}
