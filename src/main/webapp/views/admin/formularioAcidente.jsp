<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Novo Usuário</h2>
	<form role="form" action="${pageContext.request.contextPath}/salvarAcidente" method="post">
		<div class="form-group">
			<label for="data">Data</label> 
			<input
				type="date" class="form-control" id="_date" name="_date"
				pattern="AAAA-MM-DD"
				value="${acidente.date}"/>
		</div>
		<div class="form-group">
			<label for="location">Localização</label> 
			<input
				type="text" class="form-control" id="_localizacao" name="_localizacao"
				placeholder="Localização..."
				value="${acidente.location}"/>
		</div>
		<div class="form-group">
			<label for="numVic">Número de vítimas</label> 
			<input
				type="text" class="form-control" id="_nVitimas" name="_nVitimas"
				placeholder="Número de vítimas..."
				value="${acidente.numVictims}"/>
		</div>
		<div class="form-group">
			<label for="descript">Descrição</label> 
			<input
				type="text" class="form-control" id="_descricao" name="_descricao"
				placeholder="Descrição..."
				value="${acidente.description}"/>
		</div>
		<div class="form-group">
			<label for="tipo">Tipo</label> 
			<input
				type="text" class="form-control" id="_tipo" name="_tipo"
				placeholder="Tipo..."
				value="${acidente.type}"/>
		</div>
		<div class="form-group">
			<label for="status">Status</label> 
			<input
				type="text" class="form-control" id="_status" name="_status"
				placeholder="Status..."
				value="${acidente.status}"/>
		</div>
		<input type="hidden" name="id" id="id" value="${acidente.id}"/>
		<button type="submit" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="../footer.jsp"%>