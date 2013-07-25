package rd.servlet.layout;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rd.mgr.layout.ILayoutMgr;
import rd.mgr.layout.Layout;
import rd.servlet.ActionServlet;
import rd.servlet.JSonResult;
import rd.util.ComponentFactory;
import rd.util.GeneralUtil;
import rd.util.StringUtil;

import com.google.gson.Gson;

@WebServlet(name="LayoutController", urlPatterns={"/admin/layout/controller"})
public class LayoutController extends ActionServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().write("LayoutController online");
	}
	
	@Override
	protected JSonResult performAction(EntityManager eMgr,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		JSonResult result = new JSonResult();
		String action = getAction(req);
		if(action == null){
			// default action??
		}else  if(action.equalsIgnoreCase("retrieveAll")){
			result.setObj(retrieveLayouts(eMgr, req, resp));
			result.addMsg("The layouts have been reetrieve sucessfully.");
			
		}else  if(action.equalsIgnoreCase("enableLayout")){
			result.setObj(enableLayout(eMgr, req, resp));
			result.addMsg("The layout is enabled.");
			
		}else if(action.equalsIgnoreCase("save")){
			result.setObj(saveLayouts(eMgr, req, resp));
			result.addMsg("The layouts have been saved.");
		}else{
			result.addMsg("The action " + action + " is not implemented on the servlet " + this.getClass().getName());
		}
		return result;
		
	}
	
	/**
	 * 
	 * @param eMgr
	 * @param req
	 * @param resp
	 * @return  All layouts so no second ajax call must be made to refresh the layout table
	 */
	private Layout[] enableLayout(EntityManager eMgr, HttpServletRequest req, HttpServletResponse resp){
		String s = req.getParameter("id");
		long id = Long.parseLong(s);
		
		Layout old = getLayoutMgr().getSelectedLayout(eMgr)[0];
		old.setEnabled(false);
		
		Layout cur = getLayoutMgr().getLayoutByID(eMgr, id);
		cur.setEnabled(true);
		
		
		getLayoutMgr().saveLayouts(eMgr, new Layout[]{cur, old});
		
		return getLayoutMgr().getAllLayouts(eMgr);
	}
	
	private Layout[] retrieveLayouts(EntityManager eMgr, HttpServletRequest req, HttpServletResponse resp){
		return getLayoutMgr().getAllLayouts(eMgr);
	}
	
	private Layout[] saveLayouts(EntityManager eMgr, HttpServletRequest req, HttpServletResponse resp){
		String s= req.getParameter("obj");
		if(StringUtil.isNull(s)){
			return null;
		}
		
		Gson gson = GeneralUtil.getGSON();
		Layout[] layouts = gson.fromJson(s, Layout[].class);
		return getLayoutMgr().saveLayouts(eMgr, layouts);
	}
	
	
	private ILayoutMgr	 getLayoutMgr(){
		return ComponentFactory.getLayoutMgr();
	}
}
