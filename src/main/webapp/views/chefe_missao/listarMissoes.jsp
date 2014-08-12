<%@include file="header.jsp"%>
<div class="container custom_table">
	<div style="float: right">
		<h2 class="list_title">Missões</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 5%;">ID</th>  
	            <th  style="width: 20%;">Descrição</th>  
	            <th  style="width: 20%;">Status</th>  
	            <th  style="width: 20%;">Prioridade</th>  
	            <th  style="width: 20%;">ID Acidente</th>  
	            <th  style="width: 20%;">Chefe de missão</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${missions}" var="mission">
			          <tr>  
			            <td class="text-left">${mission.id}</td>  
			            <td class="text-left">${mission.description}</td>  
			            <td class="text-left">${mission.statusAlias}</td>  
			            <td class="text-left">${mission.priorityAlias}</td>  
			            <td class="text-left">${mission.accident.id}</td>  
			            <c:if test="${not empty mission.chefeMissao}">
			            	<td class="text-left">${mission.chefeMissao.username}</td>
			            </c:if>
			            <c:if test="${empty mission.chefeMissao}">
			            	<td class="text-left">Não alocada</td>
			            </c:if>
			              
			            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharMissao?id=${mission.id}">Editar</a></td>
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		
		<!-- <a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novaMissao" role="button">Cadastrar Missão</a>-->
	</div>
	<c:if test="${not empty msgSucesso}">
		<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
	</c:if>
</div>
<%@include file="footer.jsp"%>
