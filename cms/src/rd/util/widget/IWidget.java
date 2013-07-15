package rd.util.widget;

import java.util.Vector;

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
	
	/**
	 * This is where  you add the widget css and javascript files to the list
	 * 
	 * @param stylesAndScripts
	 * @return
	 */
	public Vector<String> addStylesAndScripts(Vector<String> stylesAndScripts) ;
}
