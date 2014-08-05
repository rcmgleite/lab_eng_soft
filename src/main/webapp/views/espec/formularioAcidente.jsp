<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Cadastro Acidente</h2>
	<form role="form" action="${pageContext.request.contextPath}/salvarAcidente" method="post">
		<div class="form-group">
			<label for="data">Data</label> 
			<input
				type="date" class="form-control" id="_date" name="_date"
				pattern="AAAA-MM-DD"
				value="${acidente.date}" disabled/>
		</div>
		<div class="form-group">
			<label for="location">Localização</label> 
			<input
				type="text" class="form-control" id="_localizacao" name="_localizacao"
				placeholder="Localização..."
				value="${acidente.location}" disabled/>
		</div>
		<div class="form-group">
			<label for="numVic">Número de vítimas</label> 
			<input
				type="text" class="form-control" id="_nVitimas" name="_nVitimas"
				placeholder="Número de vítimas..."
				value="${acidente.numVictims}" disabled/>
		</div>
		<div class="form-group">
			<label for="descript">Descrição</label> 
			<input
				type="text" class="form-control" id="_descricao" name="_descricao"
				placeholder="Descrição..."
				value="${acidente.description}" disabled/>
		</div>
		
		<div class="form-group">
			<label for="permissao">Tipo</label>
			<select name="selected_type" disabled>
  				<c:forEach items="${ac_types}" var="type">
						<c:if test="${acidente.type == type.ordinal()}">
						    <option selected="selected" value="${type}">
						        ${type}
						    </option>
						</c:if>  									    
						<c:if test="${acidente.type != type.ordinal()}">
						    <option value="${type}">
						        ${type}
						    </option>
						</c:if>  		
  				</c:forEach>
			</select> 
		</div>
		
		<div class="form-group">
			<label for="descript">URL CMS</label> 
			<input
				type="text" class="form-control" id="_urlCMS" name="_urlCMS"
				placeholder="url..."
				value="${acidente.urlCMS}" disabled/>
		</div>
		<c:if test="${not empty acidente.urlCMS}">
		<div class="c_video">
			<iframe width="600" height="400" src="${acidente.urlCMS}" frameborder="0" allowfullscreen></iframe>
		</div>
		</c:if>
		
		<input type="hidden" name="id" id="id" value="${acidente.id}"/>
		<input type="hidden" name="_status" id="_status" value="${acidente.status}"/>
	</form>

	<c:if test="${not empty acidente}">
		<div style="margin-top: 80px">
		<h3 class="list_title">Missões associadas</h3>
			<c:if test="${not empty acidente.missions}">
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
						<c:forEach items="${acidente.missions}" var="mission">
					          <tr>  
					            <td class="text-left">${mission.id}</td>  
					            <td class="text-left">${mission.description}</td>  
					            <td class="text-left">${mission.statusAlias}</td>  
					            <td class="text-left">${mission.priorityAlias}</td>  
					            <td class="text-left">${mission.accident.id}</td>  
					            <td class="text-left"><a href="${pageContext.request.contextPath}/detalharMissao?id=${mission.id}">Editar</a>
					            <td class="text-left"><a href="${pageContext.request.contextPath}/removerMissao?id=${mission.id}">Remover</a></td>  
					          </tr>				
						</c:forEach>
				
					</tbody>  
				</table>
			</c:if>
			<c:if test="${empty acidente.missions}">
				<h4>Nenhuma missão associada a este acidente</h4>
			</c:if>
			<a class="btn btn-primary btn-lg pull-right" href="${pageContext.request.contextPath}/novaMissao?id_acidente=${acidente.id}">Cadastrar missão</a>
		</div>
	</c:if>
	<c:if test="${not empty msgSucesso}">
		<span class="label label-success" style="font-size: 14px;">${msgSucesso}</span>
	</c:if>
	
</div>
<%@include file="../footer.jsp"%>