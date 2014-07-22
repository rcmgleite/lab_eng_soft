<%@include file="header.jsp"%>
<div class="container" style="margin-left: 6%; margin-top: 2%">
	<div style="float: right">
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
		<h2 class="list_title">Missões</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 10%;">ID</th>  
	            <th  style="width: 10%;">Descrição</th>  
	            <th  style="width: 10%;">Status</th>  
	            <th  style="width: 10%;">Prioridade</th>  
	            <th  style="width: 10%;">ID Acidente</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${missions}" var="mission">
			          <tr>  
			            <td class="text-left">${mission.id}</td>  
			            <td class="text-left">${mission.description}</td>  
			            <td class="text-left">${mission.status}</td>  
			            <td class="text-left">${mission.priority}</td>  
			            <td class="text-left">${mission.accident.id}</td>  
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharMissao?id=${mission.id}">Editar</a>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/removerMissao?id=${mission.id}">Remover</a></td>  
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		
		<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novaMissao" role="button">Cadastrar Missão</a>
	</div>
</div>
<%@include file="../footer.jsp"%>
