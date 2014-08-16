<%@include file="header.jsp"%>
<br>
<div class="container custom_table">
	<h2 class="list_title">Relatório Acidentes</h2>
	<form role="form" action="${pageContext.request.contextPath}/relatorioAcidentes" method="post">
		<div class="form-group">
			<label for="data">Data inicial</label> 
			<input
				type="date" class="form-control" id="_initDate" name="_initDate"
				pattern="AAAA-MM-DD"
				value="${acidente.date}"/>
		</div>

		<div class="form-group">
			<label for="data">Data final</label> 
			<input
				type="date" class="form-control" id="_finalDate" name="_finalDate"
				pattern="AAAA-MM-DD"
				value="${acidente.date}"/>
		</div>
		
		<div class="form-group">
			<label for="numVic">Número de vítimas</label> 
			<input
				type="text" class="form-control" id="_nVitimas" name="_nVitimas"
				placeholder="Número de vítimas..."
				value="${acidente.numVictims}"/>
		</div>
		
		<div class="form-group">
			<label for="permissao">Tipo</label>
			<select name="selected_type">
				<option value="">
					Qualquer
				</option>
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
			<label for="permissao">Status</label>
			<select name="selected_status">
				<option value="">
					Qualquer
				</option>
  				<c:forEach items="${ac_status}" var="status">
						<c:if test="${acidente.type == status.ordinal()}">
						    <option selected="selected" value="${status}">
						        ${status}
						    </option>
						</c:if>  									    
						<c:if test="${acidente.type != status.ordinal()}">
						    <option value="${status}">
						        ${status}
						    </option>
						</c:if>  		
  				</c:forEach>
			</select> 
		</div>
		
		<input type="hidden" name="id" id="id" value="${acidente.id}"/>
		<input type="hidden" name="_status" id="_status" value="${acidente.status}"/>
		<button type="submit" 
		class="btn btn-primary btn-lg" 
		style="float: right"
		>gerar relatório</button>
	</form>

	<c:if test="${not empty errorMsg}">
		<span class="label label-danger" style="font-size: 14px;">${errorMsg}</span>
	</c:if>
	
</div>
<%@include file="footer.jsp"%>