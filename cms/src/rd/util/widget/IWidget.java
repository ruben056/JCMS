package rd.util.widget;

import javax.persistence.EntityManager;

public interface IWidget {

	public String getReplacePattern();
	public String toHTML(EntityManager eMgr);
	
	/**
	 * If this returns true, it means the widget can only be used once on a page
	 * 
	 * @return
	 */
	public boolean isOnePerPage();
}
