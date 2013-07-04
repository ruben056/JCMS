package rd.mgr.page;


import javax.persistence.EntityManager;



public interface IPageMgr {

	public Page[] getAvailableParentPages(EntityManager eMgr, long parentID, long pageID);
	
	public Page[] getPagesForParent(EntityManager eMgr, long parentID);
	
	public Page savePage(EntityManager eMgr, Page page);
	
	public Page[] savePages(EntityManager eMgr, Page[] pages);
	
	public Page getPageById(EntityManager eMgr, long id);
	
	public void deletePage(EntityManager eMgr, long id);
	
	public Page[] getPageByName(EntityManager eMgr, String name);
	
	public Page[] getHomePage(EntityManager eMgr);
	
	public Page[] getHomePageAndNot(EntityManager eMgr, int pageID);
}
