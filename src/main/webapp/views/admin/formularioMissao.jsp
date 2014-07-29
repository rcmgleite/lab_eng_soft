<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Cadastro Missao</h2>
	<h4>ID do acidente: ${id_acidente}</h4>
	<form role="form"
		action="${pageContext.request.contextPath}/salvarMissao" method="post">
		<div class="form-group">
			<label for="nome">Descrição</label> 
			<input
				type="text" class="form-control" id="description" name="description"
				placeholder="descrição..."
				value="${mission.description}"/>
		</div>
		
		<div class="form-group">
			<label for="telefone">Status</label>
			<select name="selected_status">
  				<c:forEach items="${mStatus}" var="mStatus">
						<c:if test="${mission.status == mStatus.ordinal()}">
						    <option selected="selected" value="${mStatus}">
						        ${mStatus}
						    </option>
						</c:if>  									    
						<c:if test="${mission.status != mStatus.ordinal()}">
						    <option value="${mStatus}">
						        ${mStatus}
						    </option>
						</c:if>  		
  				</c:forEach>
			</select> 
		</div>
		
		<div class="form-group">
			<label for="telefone">Prioridade</label>
			<select name="selected_priority">
  				<c:forEach items="${mPriority}" var="mPriority">
						<c:if test="${mission.priority == mPriority.ordinal()}">
						    <option selected="selected" value="${mPriority}">
						        ${mPriority}
						    </option>
						</c:if>  									    
						<c:if test="${mission.priority != mPriority.ordinal()}">
						    <option value="${mPriority}">
						        ${mPriority}
						    </option>
						</c:if>  		
  				</c:forEach>
			</select> 
		</div>
		
		<div class="form-group">
			<label for="telefone">Chefe de missão</label>
			<select name="selected_mission_head">
  				<c:forEach items="${missionHeads}" var="missionHead">
					<c:if test="${mission.chefeMissao.id == missionHead.id}">
					    <option selected="selected" value="${missionHead.id}">
					        ${missionHead.username}
					    </option>
					</c:if>  									    
					<c:if test="${mission.chefeMissao.id != missionHead.id}">
					    <option value="${missionHead.id}">
					        ${missionHead.username}
					    </option>
					</c:if>  		
  				</c:forEach>
			</select> 
		</div>

		<!--  allocatedResources  -->
		<c:if test="${not empty allocatedResources}">
			<h2 class="list_title">Recursos Alocados</h2>
			<table class="table table-striped" >  
		        <thead>  
		          <tr>  
		            <th  style="width: 45%;">Tipo de recurso</th>  
		            <th  style="width: 45%;">ID missão</th>  
		          </tr>  
		        </thead>  
		        <tbody>  
					<c:forEach items="${allocatedResources}" var="resource">
				          <tr>  
				            <td class="text-left">${resource.resourceType.name}</td>  
				            <td class="text-left"> ${mission.id} </td>
				          </tr>				
					</c:forEach>
				</tbody>  
			</table>
		</c:if>
		
		<h2 class="list_title">Recursos Disponíveis</h2>
		<table class="table table-striped" >  
	        <thead>  
	          <tr>  
	            <th  style="width: 30%;">Tipo de recurso</th>  
	            <th  style="width: 30%;">ID missão</th>  
	            <th  style="width: 30%;">Alocar?</th>  
	          </tr>  
	        </thead>  
	        <tbody>  
				<c:forEach items="${resources}" var="resource">
			          <tr>  
			            <td class="text-left">${resource.resourceType.name}</td>  
			            <td class="text-left">Não alocado</td>
			            <td class="text-left"><input type="checkbox" id="${resource.id}" name="selected_resources" value="${resource.id}"/></td>
			          </tr>				
				</c:forEach>
			</tbody>  
		</table>
			
		<input type="hidden" name="id_acidente" id="id_acidente" value="${id_acidente}" /> 
		<input type="hidden" name="id" id="id" value="${mission.id}"/>
		<button type="submit" style="float:right" class="btn btn-primary btn-lg">Salvar</button>
	</form>
</div>
<%@include file="footer.jsp"%>