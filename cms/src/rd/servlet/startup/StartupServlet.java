package rd.servlet.startup;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import rd.mgr.page.IPageMgr;
import rd.mgr.page.Page;
import rd.mgr.page.selection.GetPageHierarchy;
import rd.mgr.pagecomment.IPageCommentMgr;
import rd.mgr.pagecomment.PageComment;
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
//		pages[0].setBody("[[jCMS:rdMenu{'direction':'0' }]] <p> page1 </p> [[jCMS:testPlugin{'title': 'test argument titel'}]]");
		pages[0].setBody(StartupServlet.getBody());
		pages[0].setParentID(0);
		pages[0].setOrd(1);
		
		pages[1] = new Page("testPage2", "ook root normaal");
		pages[1].setBody("[[jCMS:rdMenu{'direction':'0' }]] <p> Page2</p>");
		pages[1].setParentID(0);
		pages[1].setOrd(2);
		
		pages[2] = new Page("testPage2-child1");
		pages[2].setBody("[[jCMS:rdMenu{'direction':'0' }]] <p> child1 </p>");
		pages[2].setParentID(2);
		pages[2].setOrd(1);
		
		pages[3] = new Page("testPage2-child2");
		pages[3].setBody("[[jCMS:rdMenu{'direction':'0' }]]<p> child2 </p>");
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

		PageComment[] pcs = new PageComment[3];
		pcs[0] = new PageComment("1.bla bal comentaar is gemakkelijk e", "van ik", "bla@hotmail.com", "www.minsite.be", pages[0]);
		pcs[1] = new PageComment("2.bla bal comentaar is gemakkelijk e", "van ik", "bla@hotmail.com", "www.minsite2.be", pages[1]);
		pcs[2] = new PageComment("3.bla bal comentaar is gemakkelijk e", "van ik", "bla@hotmail.com", "www.minsite.be", pages[0]);
		pcs = getPageCommentMgr().savePageComments(eMgr, pcs);
		GeneralUtil.logObject(pcs);
		
		pcs = getPageCommentMgr().getPageCommentsForPage(eMgr, pages[0]);
		System.out.println("pcs for first page : ");
		GeneralUtil.logObject(pcs);
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
	
	private static IPageCommentMgr  getPageCommentMgr(){
		return ComponentFactory.getPageCommentMgr();
	}
	
	private static String getBody(){
		return "<p>[[jCMS:rdMenu{&#39;direction&#39;:&#39;0&#39; }]]</p>\n\n<p><a href=\"#anker\">#anker</a></p>\n\n<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>msqdf</td>\n\t\t\t<td>fff</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>f</td>\n\t\t\t<td>lmj</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>l</td>\n\t\t\t<td>mmm</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>sdfsdf</td>\n\t\t\t<td>ffff</td>\n\t\t</tr>\n\t</tbody>\n</table>\n\n<p>qsdfqsdfqsdfqsdfsdf<img alt=\"\" src=\"/cmsUploadFolder/free-computer-wallpapers2-1.jpg\" style=\"float:left; height:188px; margin:5px; opacity:0.9; width:250px\" /></p>\n\n<p>sqfdqdf</p>\n\n<div>\n<p>&micro;&micro;&micro;<img alt=\"\" src=\"/cmsUploadFolder/cubes.jpg\" style=\"float:right; height:113px; margin:20px; width:200px\" /></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<div>\n<p>&nbsp;</p>\n\n<div>\n<p>anker testje&nbsp;<a id=\"anker\" name=\"anker\"></a></p>\n\n<div lang=\"javascript\" style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\" title=\"adv titel\">\n<p>nen div met wa informatie</p>\n\n<p>funtion(){</p>\n\n<p>&nbsp;sqldmkfj &nbsp;mslkdf msqkdf qsmkjf</p>\n\n<p>}</p>\n</div>\n\n<p>[[jCMS:pageComments{}]]</p>\n</div>\n\n<p>&nbsp;</p>\n</div>\n</div>\n";
	}
}
