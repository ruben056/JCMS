<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Arrays"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.util.Date"%>
<%
	Object o = session.getAttribute("role");
	if(o == null){
		response.sendRedirect("login.jsp");
	}else{
		String[] roles = (String[])o;
		if(Arrays.binarySearch(roles, "admin") < 0){
			response.sendRedirect("login.jsp");
		}
	}
%>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.js"></script>
<script src="/cms/js/jquery.jstree/_lib/jquery.hotkeys.js"></script>
<script src="/cms/js/jquery.jstree/_lib/jquery.cookie.js"></script>
<script src="/cms/js/jquery.jstree/jquery.jstree.js"></script>
<script src="/cms/js/jquery.livequery.js"></script>
<script src="js/admin.js"></script>
<script src="js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="js/ckeditor/contents.css">

<link rel="stylesheet" href="css/admin.css" type="text/css" />
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/themes/south-street/jquery-ui.css"
	type="text/css" />
<link rel="stylesheet" href="/cms/js/jquery.jstree/themes/default/style.css"/>
</head>
<body>
	<div id="header">
		<div id="menu-top">
			<ul>
				<li><a href="pages.jsp">Pages</a></li>
				<li><a href="users.jsp">Users</a></li>
				<li><a href="logout.jsp"> Log Out</a></li>
			</ul>
		</div>
	</div>
	<div id="wrapper">
	
	</div>
</body>
</html>