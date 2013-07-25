package rd.mgr.layout;

import javax.persistence.EntityManager;

import rd.mgr.page.Page;

public interface ILayoutEngine {

	public boolean isLayoutEngineFor(String layoutName);
	public void setPage(Page p);
	public void setTemplate(String template);
	public void setEntityMgr(EntityManager eMgr);
	public String toHTML() throws Exception;
	
}
