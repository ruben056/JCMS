package rd.servlet.page.selection;

import javax.persistence.EntityManager;

import rd.mgr.page.Page;
import rd.util.ComponentFactory;
import rd.util.ISpecialSelection;

public class GetOptionsForParentCBO implements ISpecialSelection {

	private long pageID;
	
	@Override
	public Object performSelection(EntityManager eMgr) {
		StringBuffer result = new StringBuffer("<option value='0'> -- none -- </option>");
		Page cur = ComponentFactory.getPageMgr().getPageById(eMgr, pageID);
		long parentID = (cur != null) ? cur.getParentID(): 0;
		getAvailableParents(eMgr, 0, pageID, 0, parentID, result);
		
		return result.toString();
	}
	
	public void setPageID(long pageID){
		this.pageID = pageID;
	}
	
	private void getAvailableParents(EntityManager eMgr, long x, long id, int cntr, long parentID, StringBuffer result){
		
		Page[] pages = ComponentFactory.getPageMgr().getAvailableParentPages(eMgr, x, id);
		for (int i = 0; i < pages.length; i++) {
			Page cur= pages[i];
			result.append("<option value='" + cur.getId() +"' title='" + cur.getName() + "'");
			if(cur.getId() == parentID){
				result.append(" selected='selected'");
			}
			result.append(">");
			// some indenting
			for (int j = 0; j < cntr; j++) {
				result.append("&nbsp;");
			}
			String name = (cur.getName().length() > 20)? cur.getName().substring(0, 17): cur.getName();
			result.append(name).append("</option>");
			getAvailableParents(eMgr, cur.getId(), id, cntr++, parentID, result);
		}
	}
}
