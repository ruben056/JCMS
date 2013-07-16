package rd.mgr.pagecomment;

import javax.persistence.EntityManager;

import rd.mgr.page.Page;

public interface IPageCommentMgr {

	public PageComment[] savePageComments(EntityManager eMgr, PageComment[] pcs) ;
	
	public PageComment[] getPageCommentsForPage(EntityManager eMgr, Page p);
}
