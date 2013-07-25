package rd.mgr.layout;

import javax.persistence.EntityManager;

public interface ILayoutMgr {

//	public Layout getWebSiteById(EntityManager eMgr, long id);
	
	/**
	 * Save layouts and make sure exactly one layout is  enabled/selected
	 * 
	 * @param eMgr
	 * @param site
	 * @return
	 */
	public Layout[] saveLayouts(EntityManager eMgr, Layout[] layouts);
	
	/**
	 * retrieve the enabled/selected layout
	 * 
	 * @param eMgr
	 * @return
	 */
	public Layout[] getSelectedLayout(EntityManager eMgr); 
	
	/**
	 * Retrieve all layouts 
	 * 
	 * @param eMgr
	 * @return
	 */
	public Layout[] getAllLayouts(EntityManager eMgr);
	
	public Layout getLayoutByID(EntityManager eMgr, long id);
}
