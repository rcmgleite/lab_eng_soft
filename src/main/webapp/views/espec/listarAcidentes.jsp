<%@include file="header.jsp"%>
<div class="container custom_table">
	<div style="float: right">
		<h2 class="list_title">Acidentes</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 10%;">ID</th>  
	            <th  style="width: 10%;">Data</th>  
	            <th  style="width: 10%;">Localiza��o</th>  
	            <th  style="width: 10%;">N� V�timas</th>  
	            <th  style="width: 10%;">Descri��o</th>  
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
			            <td class="text-left">${accident.typeAlias}</td>
			            <td class="text-left">${accident.statusAlias}</td>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharAcidente?id=${accident.id}">Detalhes</a>
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success success_msg" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
	</div>
</div>
<%@include file="footer.jsp"%>
