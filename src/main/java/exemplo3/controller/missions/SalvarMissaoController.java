package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.AccidentStatusManager;
import utils.ProjectEnums;
import exemplo3.dao.GenericDAO;
import exemplo3.model.Accident;
import exemplo3.model.Mission;
import exemplo3.model.Resource;
import exemplo3.model.User;

@WebServlet("/salvarMissao")
public class SalvarMissaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarMissaoController() {
	}

//	private MissionDAO mDao = new MissionDAO();
//	private AccidentDAO acDao = new AccidentDAO();
	private GenericDAO dao = new GenericDAO();
//	private RecursoDAO recDAO = new RecursoDAO();
//	private UserDAO uDAO = new UserDAO();

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
			List<Resource> _resources = null;
			
			if(selected_resources != null){
				for(int i = 0; i < selected_resources.length; i++){
					selected_resources_str += selected_resources[i];
					if(i != selected_resources.length - 1)
						selected_resources_str += ",";
				}
				
				_resources = dao.getListEntityWhere("id in (" + selected_resources_str + ")", Resource.class);
			}
			//			Integer role_value = ProjectEnums.UserRoles.valueOf(new_role).ordinal();
			//			usuario.setRole(Long.parseLong(role_value.toString()));
			
			/**
			 *	Preenche o acidente vinculado a esta missão. 
			 **/
			Accident accident = dao.findByPrimaryKey(Long.parseLong(id_accident), Accident.class);
			mission.setAccident(accident);
			
			User missionHead = dao.findByPrimaryKey(Long.parseLong(missionHeadId), User.class);
			mission.setChefeMissao(missionHead);
			
			dao.salvar(mission, Mission.class);

			if(_resources != null){
				/**
				 * 	Para cada recurso selecionado vincula missão
				 **/
				for(Resource resouce: _resources){
					resouce.setMission(mission);
					dao.salvar(resouce, Resource.class);
				}
			}
			
			/**
			 * 	Por fim, falta atualizar o status do acidente caso todas as missões dele estejam finalizadas
			 * 	Perceba que recupero uma nova instância de acidente do banco para usar de parâmetro.
			 * 	Caso isso não fosse feito, pegaríamos o status de missão antigo e o update não
			 * 	funcionaria
			 **/
			accident = dao.findByPrimaryKey(Long.parseLong(id_accident), Accident.class);
			AccidentStatusManager.tryUpdateAccidentStatus(accident);
			
			request.setAttribute("msgSucesso",  "Missão salva com sucesso!");
			
			this.selectDispatcher(request, response, id_accident);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response, String id_accident) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				request.getRequestDispatcher("/detalharAcidente?id=" + id_accident).forward(request, response);
				break;
				
			case 2:
				request.setAttribute("role", "2");
				request.getRequestDispatcher("/listarMissoes").forward(request, response);
				break;
				
			case 3:
				request.setAttribute("role", "3");
				request.getRequestDispatcher("/listarMissoes").forward(request, response);
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
