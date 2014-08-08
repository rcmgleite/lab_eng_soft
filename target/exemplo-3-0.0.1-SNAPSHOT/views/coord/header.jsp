<%@page import="utils.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	if(session == null || session.getAttribute("role") == null || 
		!session.getAttribute("role").toString().equals("1"))
		response.sendRedirect("/svc/views/permissionDenied.jsp");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de gerenciamento de crises</title>

    <!-- Bootstrap -->
    <link href="/svc/css/bootstrap.min.css" rel="stylesheet">
	<link href="/svc/css/starter-template.css" rel="stylesheet">
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
          <a class="navbar-brand" href="#">CMS</a>
          
        </div>
        <div class="collapse navbar-collapse">
          <a class="navbar-brand" href="#" style="float:right">Coordenador</a>
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/login">Home</a></li>
            <li style="float: rigth"><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	<div class="row" style="float: left; margin-top: 2%; margin-left: 1%">
		<div class="span2">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="${pageContext.request.contextPath}/listarAcidentes">Acidentes</a></li>
				<li><a href="${pageContext.request.contextPath}/listarRecursos">Recursos</a></li>
				<li><a href="${pageContext.request.contextPath}/relatorios">Relat�rios</a></li>
			</ul>
		</div>
	</div>

      