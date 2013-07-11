package rd.util.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

	private static EntityManagerFactory emf = null;
	public static EntityManagerFactory getEmf(){
		if(emf == null)
			emf = Persistence.createEntityManagerFactory("cmsUnit");
		return emf;
	}
	
	
	private static EntityManagerFactory localEmf = null;
	public static EntityManagerFactory getLocalEmf(){
		if(localEmf == null)
			localEmf = Persistence.createEntityManagerFactory("cmsUnitLocal");
		return localEmf;
	}
}
