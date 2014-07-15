<%@include file="../header.jsp"%>
<br>
<div>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarUsuario" method="post">
		<div class="form-group">
			<label for="nome">Nome</label> 
			<input
				type="text" class="form-control" id="nome" name="nome"
				placeholder="Informe o nome do usuário..."
				value="${usuario.nome}"/>
		</div>
		<div class="form-group">
			<label for="email">Email</label> 
			<input
				type="email" class="form-control" id="email" name="email"
				placeholder="Informe o email do usuário..."
				value="${usuario.email}"/>
		</div>
		<div class="form-group">
			<label for="telefone">Telefone</label> 
			<input
				type="text" class="form-control" id="telefone" name="telefone"
				placeholder="Informe o telefone do usuário..."
				value="${usuario.telefone}"/>
		</div>
		<input type="hidden" name="id" id="id" value="${usuario.id}"/>
		<button type="submit" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="../footer.jsp"%>