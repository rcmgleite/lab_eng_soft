package utils;

import java.util.List;

import exemplo3.dao.MissionDAO;
import exemplo3.dao.RecursoDAO;
import exemplo3.dao.UserDAO;
import exemplo3.model.Mission;
import exemplo3.model.Resource;
import exemplo3.model.User;

public class RemoveCascade {
	public static void deallocateResources(Mission mission, MissionDAO dao, RecursoDAO recDAO){
		try {
			List<Resource> resources = mission.getResources();
			for(Resource resource : resources){
				resource.setMission(null);
				recDAO.salvarRecurso(resource);
			}
		} catch (Exception e) {
			// TODO fazer alguma coisa
			e.printStackTrace();
		}
	}

	public static void deallocateMissionsFromUser(User user, MissionDAO mDAO, UserDAO uDAO){
		try {
			List<Mission> missions = user.getMissions();
			for(Mission mission: missions){
				mission.setChefeMissao(null);
				mDAO.salvar(mission);
			}
		} catch (Exception e) {
			// TODO fazer alguma coisa
			e.printStackTrace();
		}
	}
}
