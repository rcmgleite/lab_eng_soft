package utils;

public class ProjectEnums {

	public static enum AccidentStatus{
		OPEN, IN_PROGRESS, CONCLUDED
	}
	
	public static enum AccidentType{
		VHEICLE_VHEICLE_COLLISION, VHEICLE_OBSTACLE_COLLISION, RUN_OVER, BROKEN_VHEICLE, ROLLOVER
	}
	
	public static enum UserRoles{
		ADMIN, COORD, ESPEC, CHEFE_MISSAO
	}
	
	public static AccidentStatus getStatusByInt(Integer i){
		switch(i){
			case 0:
				return AccidentStatus.OPEN;
			case 1:
				return AccidentStatus.IN_PROGRESS;
			case 2:
				return AccidentStatus.CONCLUDED;
			default:
				return null;
		}
	}

	public static AccidentType getTypeByInt(Integer i){
		switch(i){
		case 0:
			return AccidentType.VHEICLE_VHEICLE_COLLISION;
		case 1:
			return AccidentType.VHEICLE_OBSTACLE_COLLISION;
		case 2:
			return AccidentType.RUN_OVER;
		case 3:
			return AccidentType.BROKEN_VHEICLE;
		case 4:
			return AccidentType.ROLLOVER;
		default:
			return null;
		}
	}
	
	public static UserRoles getUserRoleByInt(Integer i){
		switch(i){
			case 0:
				return UserRoles.ADMIN;
			case 1:
				return UserRoles.COORD;
			case 2:
				return UserRoles.ESPEC;
			case 3:
				return UserRoles.CHEFE_MISSAO;
			default:
				return null;
		}
	}
}
