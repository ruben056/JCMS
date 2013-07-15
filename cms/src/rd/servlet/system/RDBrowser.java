package rd.servlet.system;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rd.util.config.RDProperties;

/**
 * Servlet implementation class RDBrowser
 */
@WebServlet(name = "RDBrowser", urlPatterns = {"/admin/browser/browse"})
public class RDBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RDBrowser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer sb = new StringBuffer("<html><body><ul>");
		
		String wsPath = request.getServletContext().getRealPath("");
		wsPath = wsPath.substring(0, wsPath.lastIndexOf('/'));
		
		final String path = wsPath + RDProperties.getString(RDProperties.CONTENT_UPLOAD_FOLDER);
		File f= new File(path);
		File[] files = f.listFiles();;
		for (int i = 0; i < files.length; i++) {
			File cur = files[i];
			if(cur.isDirectory()){
				sb.append("<li>" + cur.getAbsolutePath() + "</li>");
			}else{
				sb.append("<li>" + cur.getAbsolutePath() + "</li>");
			}
		}
		
		sb.append("</ul></body></html>");
		
		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("did not expect post here ...");
	}

}
