<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Relatório Missao</h2>
	<h4>ID do acidente: ${id_acidente}</h4>
	<form role="form"
		action="${pageContext.request.contextPath}/relatorioMissoes" method="post">

		<div class="form-group">
			<label for="nome">ID do Aciente</label> 
			<input
				type="text" class="form-control" id="idAcidente" name="idAcidente"
				placeholder="Qualquer"
				value=""/>
		</div>

		<div class="form-group">
			<label for="telefone">Status</label>
			<select name="selected_status">
				<option value="">
					Qualquer
				</option>
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
				<option value="">
					Qualquer
				</option>
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
				<option value="">
					Qualquer
				</option>
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

		<button type="submit" style="float:right" class="btn btn-primary btn-lg">Gerar Relatório</button>
	</form>
</div>
<%@include file="footer.jsp"%>