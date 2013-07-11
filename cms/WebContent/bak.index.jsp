<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@page import="java.util.Date"%> --%>
<%@page import="java.net.URL"%>
<%@page import="javax.swing.border.TitledBorder"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="rd.util.db.DBUtil"%>
<%@page import="rd.util.GeneralUtil"%>
<%@page import="rd.mgr.page.Page"%>
<%@page import="rd.util.ComponentFactory"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	EntityManager eMgr = DBUtil.getEmf().createEntityManager();
	try{
		eMgr.getTransaction().begin();
		String body = "";
		Page p = null;
		String o = request.getParameter("id");
		if(o != null){
			long id = Long.parseLong((String)o);
			p = ComponentFactory.getPageMgr().getPageById(eMgr, id);
// 			body = p!=null ? p.getBody() : "No page found for id " + id;
		}else{
			o = request.getParameter("name");
			if(o != null){
				Page[] pages = ComponentFactory.getPageMgr().getPageByName(eMgr, o);
				if(pages != null && pages.length > 0){
					p = pages[0];
					body = p.getBody();
				}
				/* else{
					body = "No page found for name " + o;
				 }*/
			}
			Page[] home = ComponentFactory.getPageMgr().getHomePage(eMgr);
			if(home != null && home.length > 0){
				p = home[0];
// 				body = home[0].getBody();
			}
		}	
		 
		if(p != null){
			
			final int type = p.getType();
			switch (type) {
			case 1: //normal page
				body=p.getBody();
				break;

			default:
				break;
			}
			
		}else{
			body = "No page found for id ";
		}
		
		StringBuffer metaData = new StringBuffer();
		metaData.append("<title>").append(
				p.getTitle()== null ? p.getName(): p.getTitle()).append("</title>");
		metaData.append("<script	src='http://code.jquery.com/jquery-1.9.1.js'></script>");
		metaData.append("<script	src='http://code.jquery.com/ui/1.10.3/jquery-ui.js'></script>");
		metaData.append("<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/>");
		if(p.getKeywords() != null && p.getKeywords().length() > 0)
			metaData.append("<meta http-equiv='keywords' content=" + p.getKeywords() +" />");

		// use basic template for now
		/* if(p.getTemplate()){
		} */
		String template = getServletContext().getContextPath()+"/themes/basic/default.html";
		URL u = getServletContext().getResource(template);
		%>
		
		



		<%=body %>
		
		<%
		
		eMgr.getTransaction().commit();
	}catch(Exception e){
		eMgr.getTransaction().rollback();
		e.printStackTrace();
	}finally{
		eMgr.close();
	}
	
	%>
	<%-- <h1>Working !!</h1>
	<p> Datum : <%= new Date()%> </p> --%>
</body>
</html>