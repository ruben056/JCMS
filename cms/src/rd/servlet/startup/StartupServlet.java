package rd.servlet.startup;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import rd.mgr.page.IPageMgr;
import rd.mgr.page.Page;
import rd.mgr.page.selection.GetPageHierarchy;
import rd.mgr.user.Group;
import rd.mgr.user.IUserMgr;
import rd.mgr.user.User;
import rd.util.ComponentFactory;
import rd.util.GeneralUtil;
import rd.util.ISpecialSelection;
import rd.util.db.DBUtil;

@WebServlet(name = "StartupServlet", urlPatterns ={"/admin/startup"}, loadOnStartup = 1) 
public class StartupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -608245147372050546L;

	@Override
	public void init() throws ServletException {
		EntityManager eMgr = DBUtil.getEmf().createEntityManager();
		eMgr.getTransaction().begin();
		try {
			 /** perform some tests and some initialization here */
			createGroups(eMgr);
			createUser(eMgr);
			testPages(eMgr);
			eMgr.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			eMgr.getTransaction().rollback();
		} finally{
			eMgr.close();
		}
	}
	
	private void testPages(EntityManager eMgr){
		
		// create some pages first
		Page[] pages = new Page[4];
		pages[0] = new Page("testPage1", "den titel");
		pages[0].setBody("<h1> page1 </h1>");
		pages[0].setParentID(0);
		pages[0].setOrd(1);
		
		pages[1] = new Page("testPage2", "ook root normaal");
		pages[1].setBody("<h1> Page2</h1>");
		pages[1].setParentID(0);
		pages[1].setOrd(2);
		
		pages[2] = new Page("testPage2-child1");
		pages[2].setBody("<h1> child1 </h1>");
		pages[2].setParentID(2);
		pages[2].setOrd(1);
		
		pages[3] = new Page("testPage2-child2");
		pages[3].setBody("<h1> child2 </h1>");
		pages[3].setParentID(2);
		pages[3].setOrd(2);
		
		pages = getPageMgr().savePages(eMgr, pages);
		
		// retrieve hierarchy 
		ISpecialSelection sel = new GetPageHierarchy();
		Object o = sel.performSelection(eMgr);
		System.out.println("Result of the special selection:");
		if(o == null){
			System.out.println("returned null");
		}else{
			System.out.println("result = " + GeneralUtil.getGSON().toJson(o));
		}
		
	}
	
	private void createGroups(EntityManager eMgr){
		
		Group[] groups = getUserMgr().getAllGroups(eMgr);
		if(groups.length > 0){
			System.out.println("Groups allready present:");
			return;					
		}
		
		groups = new Group[]{new Group("_administrator"), new Group("_superadministrator")};
		groups = getUserMgr().saveGroups(eMgr, groups);
		
		groups = getUserMgr().getAllGroups(eMgr);
		System.out.println("--curent groups --- ");
		GeneralUtil.logObject(groups);
	}
	
	private void createUser(EntityManager eMgr) {

		User[] users = getUserMgr().getAllUsers(eMgr);
		if(users.length > 0){
			System.out.println("there are users in the db ... skip creation of users");
			return;
		}
		
		// create user
		User[] newUsers = new User[]{
				new User("demuynck_ruben@hotmail.com", "zimzimma"),
				new User("rudy@hotmail.com", "zimzimma")};
		Group[] grps = getUserMgr().getAllGroups(eMgr);
		newUsers[0].addGroup(grps[0]);
		newUsers[0].setActive(true);

		newUsers[1].addGroup(grps[0]);
		newUsers[1].setActive(true);
		
		System.out.println("--- logging before saving: ---");
		GeneralUtil.logObject(newUsers);
		
		// persist user
		getUserMgr().saveUsers(eMgr, newUsers);
		System.out.println("--- logging after saving: ---");
		GeneralUtil.logObject(newUsers);
		
		// change something  (it should be persisted automatically)
		newUsers[1].setActive(false);
		newUsers[0].addGroup(grps[1]);
	}
		
	private IUserMgr getUserMgr(){
		return ComponentFactory.getUserMgr();
	}
	
	private IPageMgr getPageMgr(){
		return ComponentFactory.getPageMgr();
	}
}
