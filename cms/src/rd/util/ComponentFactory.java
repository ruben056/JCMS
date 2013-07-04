package rd.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import rd.mgr.page.IPageMgr;
import rd.mgr.user.IUserMgr;


public class ComponentFactory {

	private static ClassPathXmlApplicationContext ctx = 
			new ClassPathXmlApplicationContext("beans.xml");
	
	private static IUserMgr userMgr = null;
	public static IUserMgr getUserMgr(){
		if(userMgr == null){
			userMgr = (IUserMgr)ctx.getBean("UserMgr");
		}
		return userMgr;
	}
	
	private static IPageMgr pageMgr = null;
	public static IPageMgr getPageMgr(){
		if(pageMgr == null){
			pageMgr = (IPageMgr)ctx.getBean("PageMgr");
		}
		return pageMgr;
	}
}
