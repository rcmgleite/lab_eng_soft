<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Relatório Recursos</h2>
	<form role="form" action="${pageContext.request.contextPath}/relatorioRecursos" method="post">
		<div class="form-group">
			<label for="telefone">Tipo do Recurso</label>
			<select name="selected_resourceType">
				<option value="">
					Qualquer
				</option>
  				<c:forEach items="${resourceTypes}" var="type">
  					<c:if test="${resource.resourceType.id == type.id}">
					    <option selected="selected" value="${type.id}">
					        ${type.name} - ${type.description}
					    </option>
					</c:if>
					<c:if test="${resource.resourceType.id != type.id}">
					    <option value="${type.id}">
					        ${type.name} - ${type.description}
					    </option>
					</c:if>  			  			
  				</c:forEach>
			</select> 
		</div>
		
		<div class="form-group">
			<label for="mission_id">Id da missão</label> 
			<input
				type="text" class="form-control" id="mission_id" name="mission_id"
				placeholder="id da missão..."
				value=""/>
		</div>
		<button type="submit" 
		class="btn btn-primary btn-lg" 
		style="float: right"
		>gerar relatório</button>
	</form>

	<c:if test="${not empty errorMsg}">
		<span class="label label-danger" style="font-size: 14px;">${errorMsg}</span>
	</c:if>
	
</div>
<%@include file="../footer.jsp"%>