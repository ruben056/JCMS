package rd.util;

import javax.persistence.EntityManager;

public interface ISpecialSelection {

	public Object performSelection(EntityManager eMgr);
}
