<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String user = request.getParameter("email");
String pass = request.getParameter("password");

//TODO check username from db
if(user != null && user.equals("demuynck_ruben@hotmail.com") 
	&& pass != null && pass.equals("zimzimma")){
	// TODO set list of all userroles
	session.setAttribute("role", new String[]{"admin"});
	response.sendRedirect("/cms/admin");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/themes/south-street/jquery-ui.css" />
<script src="js/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<title>Administrator Login</title>
</head>
<body>

<div class="tabs">
		,
		<ul>
			<li><a href="#tab1">Login</a>
			</li>
			<li><a href="#tab2">Forgotten Password</a>
			</li>
		</ul>
		<div id="tab1">
			<form method="post"action="login.jsp">
				<table>
					<tr>
						<th>email</th>
						<td><input id="email" name="email" type="email" /></td>
					</tr>
					<tr>
						<th>password</th>
						<td><input type="password" name="password" /></td>
					</tr>
<!-- 					<tr id="captcha"> -->
<!-- 						<th>captcha</th> -->
						<td><?php echo $captcha; ?></td>
<!-- 					</tr> -->
					<tr>
						<th colspan="2" align="right"><input name="action" type="submit"
							value="login" class="login" /></th>
					</tr>
				</table>
			</form>
		</div>
		<div id="tab2">
			<form method="post" action="TODO">
				<table>
					<tr>
						<th>email</th>
						<td><input id="email" type="text" name="email" /></td>
					</tr>
					<tr>
						<th colspan="2" align="right"><input name="action" type="submit"
							value="resend my password" class="login" /></th>
					</tr>
				</table>
			</form>
		</div>
	</div>	
</body>
</html>