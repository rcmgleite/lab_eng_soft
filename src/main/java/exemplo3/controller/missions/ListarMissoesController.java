package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.MissionDAO;
import exemplo3.dao.UserDAO;
import exemplo3.model.Mission;
import exemplo3.model.User;

@WebServlet("/listarMissoes")
public class ListarMissoesController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -667869399998448076L;
	
	private MissionDAO dao = new MissionDAO();
	private UserDAO uDao = new UserDAO();
	
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
			List<Mission> missions = null;
			
			/**
			 *	Caso não seja chefe de missão tentando acessar, retorno todas as missões 
			 **/
			String strRole = request.getSession().getAttribute("role").toString();
			if(strRole != null && !strRole.equals("3")){
				missions = dao.getMissions();
			}
			else{
				String uname = request.getSession().getAttribute("user").toString();
				User user = uDao.getSingleUserWhere("username like('" + uname + "')");
				missions = dao.getMissionsWhere("chefeMissao.id = " + user.getId());
			}
			for(Mission mission: missions){
				mission.setStatusAlias();
				mission.setPriorityAlias();
			}
			
			request.setAttribute("missions", missions);
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
				request.getRequestDispatcher("/views/admin/listarMissoes.jsp").forward(request, response);
				break;
				
			case 2:
				request.setAttribute("role", "2");
				request.getRequestDispatcher("/listarAcidentes").forward(request, response);
				break;
				
			case 3:
				request.setAttribute("role", "3");
				request.getRequestDispatcher("/views/chefe_missao/listarMissoes.jsp").forward(request, response);
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
