<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Novo Usuário</h2>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarUsuario" method="post">
		<div class="form-group">
			<label for="nome">Username</label> 
			<input
				type="text" class="form-control" id="username" name="username"
				placeholder="username..."
				value="${usuario.username}"/>
		</div>
		<div class="form-group">
			<label for="email">Password</label> 
			<input
				type="password" class="form-control" id="password" name="password"
				placeholder="password..."
				value="${usuario.password}"/>
		</div>
		<!--  <div class="form-group">
			<label for="telefone">Permissão</label> 
			<input
				type="text" class="form-control" id="role" name="role"
				placeholder="permissão..."
				value="${usuario.role}"/>
		</div>
		-->
		<div class="form-group">
			<label for="telefone">Permissão</label>
			<select name="selected_role">
  				<c:forEach items="${roles}" var="role">
					<c:if test="${usuario.role == role.ordinal()}">
					    <option selected="selected" value="${role}">
					        ${role}
					    </option>
					</c:if>  									    
					<c:if test="${usuario.role != role.ordinal()}">
					    <option value="${role}">
					        ${role}
					    </option>
					</c:if>  									    
  				</c:forEach>
			</select> 
		</div>
		
		
		<input type="hidden" name="id" id="id" value="${usuario.id}"/>
		<button type="submit" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="../footer.jsp"%>