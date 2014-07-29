package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import exemplo3.dao.AccidentDAO;
import exemplo3.dao.MissionDAO;
import exemplo3.dao.RecursoDAO;
import exemplo3.dao.UserDAO;
import exemplo3.model.Accident;
import exemplo3.model.Mission;
import exemplo3.model.Resource;
import exemplo3.model.User;

@WebServlet("/salvarMissao")
public class SalvarMissaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarMissaoController() {
	}

	private MissionDAO dao = new MissionDAO();
	private AccidentDAO acDao = new AccidentDAO();
	private RecursoDAO recDAO = new RecursoDAO();
	private UserDAO uDAO = new UserDAO();

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
			String id = request.getParameter("id");
			String id_accident = request.getParameter("id_acidente");
			String description = request.getParameter("description");
			String status = request.getParameter("selected_status");
			String priority = request.getParameter("selected_priority");
			String missionHeadId = request.getParameter("selected_mission_head");
			
			Mission mission = new Mission();
			if (id != null && !id.equals("") ){
				mission.setId(Long.parseLong(id));
			}
			mission.setDescription(description);
			
			Integer priorityValue = ProjectEnums.MissionPriority.valueOf(priority).ordinal(); 
			mission.setPriority(Long.parseLong(priorityValue.toString()));

			Integer statusValue = ProjectEnums.MissionStatus.valueOf(status).ordinal();
			mission.setStatus(Long.parseLong(statusValue.toString()));
			
			String[] selected_resources = request.getParameterValues("selected_resources");
			String selected_resources_str = "";
			
			for(int i = 0; i < selected_resources.length; i++){
				selected_resources_str += selected_resources[i];
				if(i != selected_resources.length - 1)
					selected_resources_str += ",";
			}
			
			List<Resource> _resources = recDAO.getResourcesWhere("id in (" + selected_resources_str + ")");
			
			//			Integer role_value = ProjectEnums.UserRoles.valueOf(new_role).ordinal();
			//			usuario.setRole(Long.parseLong(role_value.toString()));
			
			/**
			 *	Preenche o acidente vinculado a esta missão. 
			 **/
			Accident accident = acDao.findByPrimaryKey(Long.parseLong(id_accident));
			mission.setAccident(accident);
			
			User missionHead = uDAO.findByPrimaryKey(Long.parseLong(missionHeadId));
			mission.setChefeMissao(missionHead);
			
			dao.salvar(mission);

			/**
			 * 	Para cada recurso selecionado vincula missão
			 **/
			for(Resource resouce: _resources){
				resouce.setMission(mission);
				recDAO.salvarRecurso(resouce);
			}
			
			request.setAttribute("msgSucesso",  "Missão salva com sucesso!");
			
			request.getRequestDispatcher("/detalharAcidente?id=" + id_accident).forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
}
