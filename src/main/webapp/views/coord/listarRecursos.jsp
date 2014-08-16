<%@include file="header.jsp"%>
<div class="container custom_table">
	<div style="float: right">
		<h2 class="list_title">Recursos</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 30%;">ID</th>  
	            <th  style="width: 30%;">Tipo</th>  
	            <th  style="width: 30%;">ID Missão</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${resources}" var="resource">
			          <tr>  
			            <td class="text-left">${resource.id}</td>
			            <td class="text-left">${resource.resourceType.name}</td>
			            <c:if test="${not empty resource.mission.id}">
			            	<td class="text-left">${resource.mission.id}</td>
			            </c:if>
			            <c:if test="${empty resource.mission.id}">
			            	<td class="text-left">não alocado</td>
			            </c:if>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharRecurso?id=${resource.id}">Detalhar</a>
			          	<td class="text-left"><a 
			            	onclick="confirme_remove(${resource.id}, 'Resource')" 
			            	style="cursor: pointer">
			            	Remover
	            			</a>
            			</td>
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
		<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novoRecurso" role="button">Cadastrar Recurso</a>
	</div>
</div>
<%@include file="footer.jsp"%>
