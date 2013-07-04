<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@page import="java.util.Date"%> --%>
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
			body = p!=null ? p.getBody() : "No page found for id " + id;
		}else{
			o = request.getParameter("name");
			if(o != null){
				Page[] pages = ComponentFactory.getPageMgr().getPageByName(eMgr, o);
				if(pages != null && pages.length > 0){
					p = pages[0];
					body = p.getBody();
				}else{
					body = "No page found for name " + o;
				}
			}
			// TODO find the homepage!!
			Page[] home = ComponentFactory.getPageMgr().getHomePage(eMgr);
			if(home != null && home.length > 0){
				body = home[0].getBody();
			}
		}	
		 
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