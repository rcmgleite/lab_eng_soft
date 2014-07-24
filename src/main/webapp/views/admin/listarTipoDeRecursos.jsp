<%@include file="header.jsp"%>
<div class="container custom_table">
	<div style="float: right">
		<h2 class="list_title">Tipos de Recurso</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 30%;">ID</th>  
	            <th  style="width: 30%;">Nome</th>  
	            <th  style="width: 30%;">Descrição</th>  
	            <th  style="width: 30%;">Externo</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${resources}" var="resource">
			          <tr>  
			            <td class="text-left">${resource.id}</td>
			            <td class="text-left">${resource.name}</td>
			            <td class="text-left">${resource.description}</td>
			            <td class="text-left">${resource.external}</td>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharTipoRecurso?id=${resource.id}">Detalhar</a>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/removerTipoRecurso?id=${resource.id}">Remover</a></td>  
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		
		<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novoTipoRecurso" role="button">Cadastrar Tipo Recurso</a>
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success success_msg" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
	</div>
</div>
<%@include file="../footer.jsp"%>
