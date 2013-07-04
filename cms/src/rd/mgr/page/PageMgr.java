package rd.mgr.page;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PageMgr implements IPageMgr {

	@Override
	public Page savePage(EntityManager eMgr, Page page) {
		return savePages(eMgr, new Page[]{page})[0];
	}

	@Override
	public Page[] savePages(EntityManager eMgr, Page[] pages) {
		
		
		if(pages == null || pages.length == 0){
			return new Page[0];
		}
		
		Vector<Long> homeIDs = new Vector<Long>(); 
		for (int i = 0; i < pages.length; i++) {
			Page page = pages[i];
			if(page.getId() <= 0){
				eMgr.persist(page);
			}else{
				eMgr.merge(page);
			}
			if((page.getSpecial()&1) == 1)
				homeIDs.add(page.getId());
		}
		
		/* check for exactly one home page, if multiple choose one of the new ones, if none make one of the new ones homepage */
		if(homeIDs.size() > 0){
			Page[] homes = getHomePage(eMgr);
			if(homes.length == 0){
				pages[0].setSpecial(pages[0].getSpecial()|1);
			}else if(homes.length > 1){
				boolean foundHome = false;
				for (int i = 0; i < homes.length; i++) {
					Page cur = homes[i];
					if(!foundHome && Arrays.binarySearch(homeIDs.toArray(new Long[homeIDs.size()]), cur.getId()) > 0){
						foundHome = true;
					}else if(i+1 < homes.length || !foundHome){
						cur.setSpecial(cur.getSpecial()&0);
					}
				}
			}
		}
		
		return pages;
	}

	@Override
	public Page[] getPagesForParent(EntityManager eMgr, long parentID) {
		
		TypedQuery<Page> qry = eMgr.createNamedQuery("getPagesForParent", Page.class);
		qry.setParameter("parent", parentID);
		return convertToArr(qry.getResultList());
	}
	
	public Page[] getAvailableParentPages(EntityManager eMgr, long parentID, long pageID){
		TypedQuery<Page> qry = eMgr.createNamedQuery("getAvailableParentPages", Page.class);
		qry.setParameter("parent", parentID);
		qry.setParameter("id", pageID);
		return convertToArr(qry.getResultList());
	}

	private Page[] convertToArr(List<Page> pages) {
		Page[] result = new Page[pages.size()];
		Iterator<Page> it = pages.iterator();
		for(int i = 0; it.hasNext(); i++){
			result[i] = it.next();
		}
		return result;
	}
	
	public Page getPageById(EntityManager eMgr, long id){
		return eMgr.find(Page.class, id);
	}
	
	@Override
	public void deletePage(EntityManager eMgr, long id) {
		Page p = getPageById(eMgr, id);
		eMgr.remove(p);
	}
	
	@Override
	public Page[] getPageByName(EntityManager eMgr, String name) {
		TypedQuery<Page> qry = eMgr.createNamedQuery("getPageByName", Page.class);
		qry.setParameter("name", name);
		return convertToArr(qry.getResultList());
	}

	@Override
	public Page[] getHomePage(EntityManager eMgr) {
		 List<Page> pages = (List<Page>)eMgr.createNativeQuery(
				 "SELECT * FROM pages where special&1", Page.class).getResultList(); 
		return convertToArr(pages);
	}

	@Override
	public Page[] getHomePageAndNot(EntityManager eMgr, int pageID) {
		List<Page> pages = (List<Page>)eMgr.createNativeQuery(
				 "SELECT * FROM pages where special&1 and id!="+pageID, Page.class).getResultList(); 
		return convertToArr(pages);
	}
}
