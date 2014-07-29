<%@include file="header.jsp"%>

<div class="starter-template">
	<h1>CMS</h1>
</div>

<div>

	<form class ="list_title" style="width: 400px" role="form"
		action="${pageContext.request.contextPath}/login"
		method="post"
		class="form-signin">
		<div class="form-group">
			<label for="nome">username</label> <input type="text"
				class="form-control" id="username" name="username"
				placeholder="Username" />
		</div>
		<div class="form-group">
			<label for="password">senha</label> <input type="password"
				class="form-control" id="password" name="password"
				placeholder="Password" />
		</div>
		<div>
			<c:if test="${not empty erro}">
				<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
			</c:if>
		</div>
		
		<button style="float:right" type="submit" class="btn btn-primary btn-lg">Login</button>
	</form>
</div>

<%@include file="footer.jsp"%>