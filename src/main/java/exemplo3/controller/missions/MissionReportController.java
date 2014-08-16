package exemplo3.controller.missions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import utils.ProjectEnums.MissionPriority;
import utils.ProjectEnums.MissionStatus;
import exemplo3.dao.GenericDAO;
import exemplo3.dao.UtilsDAO;
import exemplo3.model.Mission;
import exemplo3.model.User;

@WebServlet("/relatorioMissoes")
public class MissionReportController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715299530091293358L;

//	private MissionDAO dao = new MissionDAO();
//	private UserDAO uDAO = new UserDAO();
	private GenericDAO dao = new GenericDAO();
	
	public MissionReportController() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			/**
			 * 	O mapa de parâmetros deve conter como key o nome do campo na classe do model
			 **/
			Map<String, String> parameters = new HashMap<String, String>();
			
			String idAcidente = request.getParameter("idAcidente");
			if(idAcidente != null && !idAcidente.equals("")){
				parameters.put("accident.id", idAcidente);
			}
			
			String status = request.getParameter("selected_status");
			if(status != null && !status.equals("")){
				Integer i_status = ProjectEnums.MissionStatus.valueOf(status).ordinal();
				parameters.put("status", i_status.toString());
			}
			
			String priority = request.getParameter("selected_priority");
			if(priority != null && !priority.equals("")){
				Integer i_priority = ProjectEnums.MissionPriority.valueOf(priority).ordinal();
				parameters.put("priority", i_priority.toString());
			}
			
			String mission_head = request.getParameter("selected_mission_head");
			if(mission_head != null && !mission_head.equals("")){
				parameters.put("chefeMissao.id", mission_head);
			}
			
			/**
			 * 	caso size == 0 está entrando na tela de seleção dos filtros
			 **/
			if(parameters.size() == 0){
				MissionStatus[] mStatus = ProjectEnums.MissionStatus.values();
				MissionPriority[] mPriority = ProjectEnums.MissionPriority.values();
				
				request.setAttribute("mStatus", mStatus);
				request.setAttribute("mPriority", mPriority);
				
				List<User> missionHeads = dao.getListEntityWhere("role = " + 
						ProjectEnums.UserRoles.CHEFE_MISSAO.ordinal(), User.class);
				request.setAttribute("missionHeads", missionHeads);
				
				this.selectDispatcher(request, response, false);
			}
			/**
			 * 	Caso contrário já deve devolver o relatório
			 **/
			else{
				String where = UtilsDAO.buildWhere(parameters, Mission.class);
				List<Mission> missions = this.dao.getListEntityWhere(where, Mission.class);
				for(Mission mission: missions){
					mission.setStatusAlias();
					mission.setPriorityAlias();
				}
				request.setAttribute("missions", missions);
				this.selectDispatcher(request, response, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
					.forward(request, response);
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response, Boolean hasParameters) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				if(!hasParameters)
					request.getRequestDispatcher("/views/admin/relatorioMissao.jsp").forward(request, response);
				else{
					request.getRequestDispatcher("/views/admin/listarMissoes.jsp").forward(request, response);
				}
				break;
	
			case 1:
				request.setAttribute("role", "1");
				if(!hasParameters)
					request.getRequestDispatcher("/views/coord/relatorioMissao.jsp").forward(request, response);
				else
					request.getRequestDispatcher("/views/coord/listarMissoes.jsp").forward(request, response);
				break;
	
			default:
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				System.out.println("Erro");
				break;
			}
		}
		else{
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}
}
