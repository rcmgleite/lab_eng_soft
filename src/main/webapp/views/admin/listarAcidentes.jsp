<%@include file="header.jsp"%>
<div class="container" style="margin-left: 6%; margin-top: 2%">
	<div style="float: right">
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
		<h2 class="list_title">Acidentes</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 10%;">ID</th>  
	            <th  style="width: 10%;">Data</th>  
	            <th  style="width: 10%;">Localização</th>  
	            <th  style="width: 10%;">Nº Vítimas</th>  
	            <th  style="width: 10%;">Descrição</th>  
	            <th  style="width: 10%;">Tipo</th>  
	            <th  style="width: 10%;">Status</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${accidents}" var="accident">
			          <tr>  
			            <td class="text-left">${accident.id}</td>  
			            <td class="text-left">${accident.date}</td>
			            <td class="text-left">${accident.location}</td>
			            <td class="text-left">${accident.numVictims}</td>
			            <td class="text-left">${accident.description}</td>
			            <td class="text-left">${accident.type}</td>
			            <td class="text-left">${accident.status}</td>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharAcidente?id=${accident.id}">Detalhes</a>
		             	<td class="text-left"><a href="${pageContext.request.contextPath}/removerAcidente?id=${accident.id}">Remover</a></td>  
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		
		<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novoAcidente" role="button">Cadastrar Acidente</a>
	</div>
</div>
<%@include file="../footer.jsp"%>
