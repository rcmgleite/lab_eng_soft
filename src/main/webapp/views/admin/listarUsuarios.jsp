<%@include file="header.jsp"%>
<div class="container custom_table">
	<div style="float: right">
		<h2 class="list_title">Usuários</h2>
		<table class="table table-striped" style="margin-left: 3%" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 30%;">ID</th>  
	            <th  style="width: 30%;">Login</th>  
	            <th  style="width: 30%;">Senha</th>  
	            <th  style="width: 30%;">Permissão</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${usuarios}" var="usuario">
			          <tr>  
			            <td class="text-left">${usuario.id}</td>  
			            <td class="text-left">${usuario.username}</td>
			            <td class="text-left">${usuario.password}</td>
			            <td class="text-left">${usuario.roleAlias}</td>
			            <td class="text-left"><a href="${pageContext.request.contextPath}/editarUsuario?id=${usuario.id}">Editar</a>
			             <td class="text-left"><a href="${pageContext.request.contextPath}/removerUsuario?id=${usuario.id}">Remover</a></td>  
			          </tr>				
				</c:forEach>
		
			</tbody>  
		</table>
		
		<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/views/admin/formularioUsuario.jsp" role="button">Novo Usuário</a>
		<c:if test="${not empty msgSucesso}">
			<span class="label label-success success_msg" style="font-size: 14px;">${msgSucesso}</span>
		</c:if>
	</div>
</div>
<%@include file="../footer.jsp"%>
