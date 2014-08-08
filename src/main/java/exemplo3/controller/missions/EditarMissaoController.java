package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import utils.ProjectEnums.MissionPriority;
import utils.ProjectEnums.MissionStatus;
import exemplo3.dao.MissionDAO;
import exemplo3.dao.RecursoDAO;
import exemplo3.dao.UserDAO;
import exemplo3.model.Mission;
import exemplo3.model.Resource;
import exemplo3.model.User;

@WebServlet("/detalharMissao")
public class EditarMissaoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025578345214393412L;
	
	private MissionDAO dao = new MissionDAO();
	private RecursoDAO recDao = new RecursoDAO();
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

			/*id da missão*/
			Long pk = Long.parseLong(request.getParameter("id"));
			Mission mission = dao.findMissionByPrimaryKey(pk);

			String id_acidente = mission.getAccident().getId().toString();
			request.setAttribute("id_acidente", id_acidente);
			
			request.setAttribute("mission", mission);
			
			List<Resource> resources = recDao.getResourcesWhere("mission.id = null");
			request.setAttribute("resources", resources);
			
			List<Resource> allocatedResources = recDao.getResourcesWhere("mission.id = " + pk);
			request.setAttribute("allocatedResources", allocatedResources);
			
			MissionStatus[] mStatus = ProjectEnums.MissionStatus.values();
			MissionPriority[] mPriority = ProjectEnums.MissionPriority.values();
			
			request.setAttribute("mStatus", mStatus);
			request.setAttribute("mPriority", mPriority);
			
			List<User> missionHeads = uDAO.getUsersWhere("role = " + ProjectEnums.UserRoles.CHEFE_MISSAO.ordinal());
			request.setAttribute("missionHeads", missionHeads);			
			
			this.selectDispatcher(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				request.getRequestDispatcher("/views/admin/formularioMissao.jsp").forward(request, response);
				break;
				
			case 2:
				request.setAttribute("role", "2");
				request.getRequestDispatcher("/views/espec/formularioMissao.jsp").forward(request, response);
				break;
				
			case 3:
				request.setAttribute("role", "3");
				request.getRequestDispatcher("/views/chefe_missao/formularioMissao.jsp").forward(request, response);
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
