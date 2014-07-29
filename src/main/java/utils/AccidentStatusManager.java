package utils;

import java.util.List;

import utils.ProjectEnums.AccidentStatus;
import exemplo3.dao.AccidentDAO;
import exemplo3.model.Accident;
import exemplo3.model.Mission;

public class AccidentStatusManager {
	
	private static AccidentDAO dao = new AccidentDAO();
	
	private static Boolean hasToUpdateStatusToConcluded(Accident accident){
		List<Mission> missions = accident.getMissions();
		
		/**
		 * 	verifica size pois caso tenha acabado de criar a missão, ela não possui missões.
		 * 	Sendo assim, logo que criamos a missão não devemos atualizar o status da mesma.
		 **/
		if(missions.size() != 0){
			for(Mission mission: missions){
				Integer status = Integer.parseInt(mission.getStatus().toString());
				if(status != ProjectEnums.MissionStatus.CONCLUDED.ordinal()){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private static Boolean hasToUpdateStatusToInProgress(Accident accident){
		Integer status = Integer.parseInt(accident.getStatus().toString());
		if(status == ProjectEnums.AccidentStatus.IN_PROGRESS.ordinal()){
			return false;
		}
		else{
			if(accident.getMissions().size() != 0){
				return true;
			}
		}
		return true;
	}
	
	
	private static void do_updateAccidentStatus(Accident accident, AccidentStatus status){
		try {
			Integer _status = status.ordinal();
			accident.setStatus(Long.parseLong(_status.toString()));
			dao.salvar(accident);
		} catch (Exception e) {
			// TODO fazer alguma coisa
			e.printStackTrace();
		}
	}
	
	public static void  tryUpdateAccidentStatus(Accident accident){
		if(hasToUpdateStatusToConcluded(accident)){
			do_updateAccidentStatus(accident, AccidentStatus.CONCLUDED);
		}
		else if(hasToUpdateStatusToInProgress(accident)){
			do_updateAccidentStatus(accident, AccidentStatus.IN_PROGRESS);
		}
	}
}
