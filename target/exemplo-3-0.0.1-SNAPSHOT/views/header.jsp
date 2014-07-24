<%@page import="utils.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(session != null || session.getAttribute("role") != null){
		SessionManager sm = new SessionManager(60 * 30);
		Boolean hasSession = sm.verifySession(request, response);
		if(hasSession){
			String role = session.getAttribute("role").toString();
			if(role.equals("0")){
				response.sendRedirect("/svc/views/admin/index_admin.jsp");			
			}
			else if(role.equals("1")){
				response.sendRedirect("/svc/views/coord/index_coord.jsp");
			}
			else if(role.equals("2")){
				response.sendRedirect("/svc/views/espec/index_espec.jsp");
			}
			else if(role.equals("3")){
				response.sendRedirect("/svc/views/chefe_missao/index_chefe_missao.jsp");
			}
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de gerenciamento de crises</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/starter-template.css" rel="stylesheet">
	<link href="/svc/css/custom.css" rel="stylesheet">
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">SVC</a>
        </div>
      </div>
    </div>


      