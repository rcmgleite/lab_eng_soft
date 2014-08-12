package utils;

import java.util.List;

import exemplo3.dao.GenericDAO;
import exemplo3.model.Mission;
import exemplo3.model.Resource;
import exemplo3.model.User;

/**
 * 	Essa classe só foi criada porque não conhecia triggers...
 * 	A maneira mais correta de se fazer o cascade seria criar triggers no banco
 * 	para que no momento que um DELETE fosse chamado, o trigger já fosse chamado e fizesse toda a atualização
 **/
public class RemoveCascade {
	public static void deallocateResources(Mission mission, GenericDAO dao){
		try {
			List<Resource> resources = mission.getResources();
			for(Resource resource : resources){
				resource.setMission(null);
				dao.salvar(resource, Resource.class);
			}
		} catch (Exception e) {
			// TODO fazer alguma coisa
			e.printStackTrace();
		}
	}

	public static void deallocateMissionsFromUser(User user, GenericDAO dao){
		try {
			List<Mission> missions = user.getMissions();
			for(Mission mission: missions){
				mission.setChefeMissao(null);
				dao.salvar(mission, Mission.class);
			}
		} catch (Exception e) {
			// TODO fazer alguma coisa
			e.printStackTrace();
		}
	}
}
