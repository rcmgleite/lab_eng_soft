<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Cadastrar Missao</h2>
	<h4>ID do acidente: ${id_acidente}</h4>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarMissao" method="post">
		<div class="form-group">
			<label for="nome">Descrição</label> 
			<input
				type="text" class="form-control" id="description" name="description"
				placeholder="descrição..."
				value="${mission.name}"/>
		</div>
		<div class="form-group">
			<label for="descricao">Status</label> 
			<input
				type="text" class="form-control" id="status" name="status"
				placeholder="Status..."
				value="${mission.status}"/>
		</div>
		<div class="form-group">
			<label for="telefone">Prioridade</label> 
			<input
				type="text" class="form-control" id="priority" name="priority"
				placeholder="Prioridades..."
				value="${mission.priority}"/>
		</div>
		<h2 class="list_title">Recursos Disponíveis</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 30%;">Tipo de recurso</th>  
	            <th  style="width: 30%;">ID missão</th>  
	            <th  style="width: 30%;">Alocar?</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${resources}" var="resource">
			          <tr>  
			            <td class="text-left">${resource.resourceType.name}</td>  
			            <td class="text-left">Não alocado</td>
			            <td class="text-left"><input type="checkbox" id="${resource.id}" name="selected_resources" value="${resource.id}"/></td>
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
	
		<input type="hidden" name="id_acidente" id="id_acidente" value="${id_acidente}" /> 
		<input type="hidden" name="id" id="id" value="${mission.id}"/>
		<button type="submit" style="float:right" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="footer.jsp"%>