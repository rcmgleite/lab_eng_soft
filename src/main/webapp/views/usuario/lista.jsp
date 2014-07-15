<%@include file="../header.jsp"%>
<div class="starter-template">
	
	<c:if test="${not empty msgSucesso}">
		<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
	</c:if>
	
	
	
	<table class="table table-striped table-nonfluid" >  
        <thead>  
          <tr>  
            <th  style="width: 5%;">ID</th>  
            <th  style="width: 60%;">Nome</th>  
            <th  style="width: 30%;">Email</th>  
            <th  style="width: 5%;">Ação</th>  
          </tr>  
        </thead>  
        <tbody>  
			<c:forEach items="${usuarios}" var="usuario">
		          <tr>  
		            <td class="text-left">${usuario.id}</td>  
		            <td class="text-left">${usuario.nome}</td>
		            <td class="text-left">${usuario.email}</td>  
		            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharUsuario?id=${usuario.id}">Editar</a>
		             <td class="text-left"><a href="${pageContext.request.contextPath}/removerUsuario?id=${usuario.id}">Remover</a></td>  
		          </tr>				
			</c:forEach>
	
		</tbody>  
	</table>
	
	<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novoUsuario" role="button">Novo Usuário</a>
</div>	
<%@include file="../footer.jsp"%>
