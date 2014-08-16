<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Cadastro Recurso</h2>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarRecurso" method="post">
		<div class="form-group">
			<label for="telefone">Tipo do Recurso</label>
			<select name="selected_resourceType">
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
		<input type="hidden" name="id" id="id" value="${resource.id}"/>
		
		<button type="submit" style="float:right" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="footer.jsp"%>