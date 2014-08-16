<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Cadastro de Usuário</h2>
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

		<div class="form-group">
			<label for="permissao">Permissão</label>
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
		
		<c:if test="${not empty usuario.missions}">
			<h2 class="list_title">Missões Associadas</h2>
			<table class="table table-striped" >  
		        <thead>  
		          <tr>  
		            <th  style="width: 20%;">ID</th>  
		            <th  style="width: 20%;">Descrição</th>  
		            <th  style="width: 20%;">Status</th>  
		            <th  style="width: 20%;">Prioridade</th>  
		            <th  style="width: 20%;">ID Acidente</th>  
		          </tr>  
		        </thead>  
		        <tbody>  
					<c:forEach items="${usuario.missions}" var="mission">
				          <tr>  
				            <td class="text-left">${mission.id}</td>  
				            <td class="text-left">${mission.description}</td>  
				            <td class="text-left">${mission.statusAlias}</td>  
				            <td class="text-left">${mission.priorityAlias}</td>  
				            <td class="text-left">${mission.accident.id}</td>  
				            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharMissao?id=${mission.id}">Editar</a></td>
				          </tr>				
					</c:forEach>
			
				</tbody>  
			</table>
		</c:if>
		
		<input type="hidden" name="id" id="id" value="${usuario.id}"/>
		<button type="submit" style="float:right" class="btn btn-primary btn-lg">Salvar</button>
	</form>
	<c:if test="${not empty errorMsg}">
		<span class="label label-danger" style="font-size: 14px;">${errorMsg}</span>
	</c:if>
</div>
<%@include file="footer.jsp"%>