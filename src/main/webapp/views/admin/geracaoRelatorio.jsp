<%@include file="header.jsp"%>
<div class="container custom_table">
	<div>
		<h2 class="list_title">Relat�rios</h2>
		<div style="margin: 30px">
			<a class="btn btn-primary btn-lg pull-left btn_report" 
				href="#" role="button">
				<br>Relat�rio geral<br>
			</a>
			<a class="btn btn-primary btn-lg pull-left btn_report" 
				href="${pageContext.request.contextPath}/relatorioAcidentes" role="button">
				<br>Relat�rio Acidentes<br>
			</a>
			<a class="btn btn-primary btn-lg pull-left btn_report" 
				href="${pageContext.request.contextPath}/relatorioMissoes" role="button">
				<br>Relat�rio Miss�es<br>
			</a>
			<a class="btn btn-primary btn-lg pull-left btn_report" href="#" role="button">
				<br>Relat�rio Recursos<br>
			</a>
		</div>
	</div>
</div>
<%@include file="../footer.jsp"%>
