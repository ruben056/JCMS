package rd.mgr.layout;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.primitives.ArrayLongList;
import org.apache.commons.collections.primitives.LongList;
import org.apache.commons.collections.primitives.adapters.LongListList;

import rd.mgr.page.Page;

public class LayoutMgr implements ILayoutMgr {

//	@Override
//	public Layout getWebSiteById(EntityManager eMgr, long id){
//		return eMgr.find(Layout.class, id);
//	}
	
	@Override
	public Layout[] saveLayouts(EntityManager eMgr, Layout[] layouts) {
		if (layouts == null || layouts.length == 0) {
			return null;
		}

		LongList ll = new ArrayLongList();		
		for (int i = 0; i < layouts.length; i++) {
			Layout cur = layouts[i];
			if (cur.getId() <= 0) {
				eMgr.persist(cur);
			} else {
				eMgr.merge(cur);
			}
			if(cur.isEnabled())
				ll.add(cur.getId());
		}
		
		Layout[] selected = getSelectedLayout(eMgr);
		if(selected.length > 0){
			if(selected.length != 1){
				short s = 0;
				for (int i = 0; i < selected.length; i++) {
					Layout cur = selected[i];
					if(ll.contains(cur.getId()) && s==0){
						s=1;
						continue;
					}
					if(s==0 && i==selected.length-1){
						break;
					}
					cur.setEnabled(false);
				}
			}
		} else{
			layouts[0].setEnabled(true); // at least one layout must be selected
		}
	
		
		return layouts;
	}

	@Override
	public Layout[] getSelectedLayout(EntityManager eMgr) {
		TypedQuery<Layout> qry = eMgr.createNamedQuery("layout.findSelected", Layout.class);
		return convertToArr(qry.getResultList());
	}
	
	@Override
	public Layout[] getAllLayouts(EntityManager eMgr) {
		TypedQuery<Layout> qry = eMgr.createNamedQuery("layout.findAll", Layout.class);
		return convertToArr(qry.getResultList());
	}

	private Layout[] convertToArr(List<Layout> sites) {
		Layout[] result = new Layout[sites.size()];
		Iterator<Layout> it = sites.iterator();
		for(int i = 0; it.hasNext(); i++){
			result[i] = it.next();
		}
		return result;
	}
	
	@Override
	public Layout getLayoutByID(EntityManager eMgr, long id){
		return eMgr.find(Layout.class, id);
	}
}
