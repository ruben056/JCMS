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
	
//	public static void main(String[] args) {
//	
//		try {
//			Context initContext = new InitialContext();
//			Context envContext  = (Context)initContext.lookup("java:/comp/env");
//			DataSource ds = (DataSource)envContext.lookup("jdbc/cms");
//			Connection conn = ds.getConnection();
//			
//			
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
