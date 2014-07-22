<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Novo Usu�rio</h2>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarUsuario" method="post">
		<div class="form-group">
			<label for="nome">Username</label> 
			<input
				type="text" class="form-control" id="username" name="username"
				placeholder="username..."
				value="${usuario.username}"/>
		</div>
		<div class="form-group">
			<label for="email">Password</label> 
			<input
				type="password" class="form-control" id="password" name="password"
				placeholder="password..."
				value="${usuario.password}"/>
		</div>
		<div class="form-group">
			<label for="telefone">Permiss�o</label> 
			<input
				type="text" class="form-control" id="role" name="role"
				placeholder="permiss�o..."
				value="${usuario.role}"/>
		</div>
		<input type="hidden" name="id" id="id" value="${usuario.id}"/>
		<button type="submit" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="../footer.jsp"%>