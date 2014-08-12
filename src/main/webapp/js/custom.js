function confirme_remove(pid, className){
	/**
	 * 	classname é usado para saber para qual action fazer a requesição
	 **/
	var req_url;
	var remove_warning;
	if(className=="User"){
		req_url = "/svc/removerUsuario?id=" + pid;
		remove_warning = "Tem certeza que deseja remover o usuário de id " + pid + "?";
	}
	else if(className == "Accident"){
		req_url = "/svc/removerAcidente?id=" + pid;
		remove_warning = "Tem certeaz que deseja remover o acidente de id " + pid + "?(Suas missões serão" +
				"deletadas e seus recursos desalocados)";
	}
	else if(className == "Mission"){
		req_url = "/svc/removerMissao?id=" + pid;
		remove_warning = "Tem certeaz que deseja remover a missão de id " + pid + "?("+
		"seus recursos serão desalocados)";
	}
	else if(className == "Resource"){
		req_url = "/svc/removerRecurso?id=" + pid;
		remove_warning = "Tem certeza que deseja remover o recurso de id " + pid + "?";
	}
	else if(className == "ResourceType"){
		req_url = "/svc/removerTipoRecurso?id=" + pid;
		remove_warning = "Tem certeza que deseja remover o tipo de recurso de id " + pid + "?";
	}
	else{
		//TODO - colocar algo de útil..não um console.log
		console.log("Nome da classe inválido");
		return
	}
	
	if(confirm(remove_warning)){
		console.log("confirmou!");
		$.ajax({
			url: req_url,
			type: 'GET',
			data:{
				//id: pid
			},
			success: function(data, textStatus, jqXHR) {
				window.location = data;
			},
		});
	}
	else{
		console.log("cancelou!");
	}
}
