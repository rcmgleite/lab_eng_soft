<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Novo Usuário</h2>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarTipoRecurso" method="post">
		<div class="form-group">
			<label for="nome">Nome</label> 
			<input
				type="text" class="form-control" id="name" name="name"
				placeholder="nome"
				value="${resource.name}"/>
		</div>
		<div class="form-group">
			<label for="descricao">Descrição</label> 
			<input
				type="text" class="form-control" id="description" name="description"
				placeholder="Descrição"
				value="${resource.description}"/>
		</div>
		<div class="form-group">
			<label for="telefone">Externo?</label> 
			<input
				type="text" class="form-control" id="external" name="external"
				placeholder="Externo"
				value="${resource.external}"/>
		</div>
		<input type="hidden" name="id" id="id" value="${resource.id}"/>
		<button type="submit" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="footer.jsp"%>