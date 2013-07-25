package rd.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rd.mgr.layout.BasicLayoutEngine;
import rd.mgr.layout.DefaultLayoutEngine;
import rd.mgr.layout.ILayoutEngine;
import rd.mgr.layout.Layout;
import rd.mgr.page.Page;
import rd.util.ComponentFactory;
import rd.util.StringUtil;
import rd.util.db.DBUtil;
import rd.util.widget.parser.ContentParser;

/**
 * This servlets retrieves a page, and renders the output.
 * 
 * Servlet implementation class RenderServlet
 */
@WebServlet(name="RenderServlet", urlPatterns={"/public"})
public class RenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = null;
		
		EntityManager eMgr = DBUtil.getEmf().createEntityManager();
		eMgr.getTransaction().begin();
		try {
			result = performAction(eMgr, request, response);
			eMgr.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
			eMgr.getTransaction().rollback();
		} finally{
			response.getWriter().write(result);
			eMgr.close();
		}
	}
	
	public String performAction(EntityManager eMgr, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception{
		
		ILayoutEngine layoutEngine = getLayoutEngine(eMgr);
		
		Page p = getPage(eMgr, req, resp);		
		String template = getTemplate(eMgr, p);
		
		layoutEngine.setPage(p);
		layoutEngine.setTemplate(template);
		layoutEngine.setEntityMgr(eMgr);
		return layoutEngine.toHTML();
		
	}
	
	private ILayoutEngine getLayoutEngine(EntityManager eMgr){
		
		Layout sel = ComponentFactory.getLayoutMgr().getSelectedLayout(eMgr)[0];
		ILayoutEngine result = null;
		ILayoutEngine[] layoutEngines = new ILayoutEngine[]{new DefaultLayoutEngine(), new BasicLayoutEngine()};
		for (int i = 0; i < layoutEngines.length; i++) {
			ILayoutEngine cur = layoutEngines[i];
			if(cur.isLayoutEngineFor(sel.getName())){
				result = cur;
				break;
			}
		}
		
		return result;
	}
	
	private String getTemplate(EntityManager eMgr, Page p){
		String template = getServletContext().getContextPath()+"/themes/basic/html/" + 
				(StringUtil.isNull(p.getTemplate())?"default":p.getTemplate()) 
						+ ".html";
		// TODO if not exists use default template anyway
		
		return template;
	}
	
	/**
	 * Retrieve page:
	 * 1. if id retrieve by id
	 * 2. if name retrieve by name
	 * 3. if no param is given retrieve home page
	 * @return
	 */
	private Page getPage(EntityManager eMgr, HttpServletRequest req, HttpServletResponse resp){
		Page p = null;
		String o = req.getParameter("id");
		if(o != null){
			long id = Long.parseLong((String)o);
			p = ComponentFactory.getPageMgr().getPageById(eMgr, id);
			if(p==null){
				//TODO throw some kind of error
			}
		}else if((o = req.getParameter("name")) != null){			
			Page[] pages = ComponentFactory.getPageMgr().getPageByName(eMgr, o);
			if(pages != null && pages.length > 0){
				p = pages[0];
			}else{
				//TODO throw some kind of error
			}
		}else{
			Page[] home = ComponentFactory.getPageMgr().getHomePage(eMgr);
			if(home != null && home.length > 0){
				p = home[0];
			}
		}
		
		return p;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("did not expect the post to be triggered here????");
	}

}
