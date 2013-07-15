package rd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

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
import rd.util.widget.RdMenu;
import rd.util.widget.parser.ContentParser;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		testRegExp();
		
		EntityManager eMgr = DBUtil.getLocalEmf().createEntityManager();		
		eMgr.getTransaction().begin();
		try {
			 /** perform some tests and some initialization here */
//			createGroups(eMgr);
//			createUser(eMgr);
//			testPages(eMgr);
//			testMenu(eMgr);
//		
			testParse(eMgr);
			eMgr.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			eMgr.getTransaction().rollback();
		} finally{
			eMgr.close();
		}
	}
	
	private static void testParse(EntityManager eMgr){
		String toParse = "<html><head></head><body><h1>testing</h1> [[jCMS:rdMenu{'direction':'0'}]] qlmsdkfj lsdfjkqlmdsfkj [[jCMS:rdMenu{'direction':'0', 'opacity':'1'}]]</body></html>";
		
		String res = ContentParser.parse(eMgr, toParse);
		System.out.println(res);
	}
	
	private static void testRegExp(){
		String pattern = "/public[/a-zA-Z0-9-]*/([a-zA-Z0-9-]+)";
		String s= "http://127.0.0.1:8080/cms/public/testPage2/testPage2-child2/pagetest";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		
		int cnt = m.groupCount();
		System.out.println("groups=" + cnt);
		while(m.find()){
			String grp = m.group(1);
			System.out.println("match = " + grp);
		}
		
	}
	
	private static void testMenu(EntityManager eMgr){
		RdMenu mnu = new RdMenu();
		String s = mnu.toHTML(eMgr);
		
		System.out.println("result:\n" + s);;
	}
	
	private static void testPages(EntityManager eMgr){
		
		// create some pages first
		Page[] pages = new Page[4];
		pages[0] = new Page("testPage1", "den titel");
		pages[0].setBody("<h1> blabla bal </h1>");
		pages[0].setParentID(0);
		pages[0].setOrd(1);
		
		pages[1] = new Page("testPage2", "ook root normaal");
		pages[1].setBody("<h1> blabla bal </h1>");
		pages[1].setParentID(0);
		pages[1].setOrd(2);
		
		pages[2] = new Page("testPage2-child1");
		pages[2].setBody("<h1> blabla bal </h1>");
		pages[2].setParentID(2);
		pages[2].setOrd(1);
		
		pages[3] = new Page("testPage2-child2");
		pages[3].setBody("<h1> blabla bal </h1>");
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
	
	private static void createGroups(EntityManager eMgr){
		
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
	
	private static void createUser(EntityManager eMgr) {

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
	
	
	private static IUserMgr getUserMgr(){
		return ComponentFactory.getUserMgr();
	}
	
	private static IPageMgr getPageMgr(){
		return ComponentFactory.getPageMgr();
	}
	
	

}
