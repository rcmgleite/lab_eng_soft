package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.MissionDAO;
import exemplo3.model.Mission;

@WebServlet("/listarMissoes")
public class ListarMissoesController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -667869399998448076L;
	
	private MissionDAO dao = new MissionDAO();
	
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
			List<Mission> missions = dao.getMissions();
			
			for(Mission mission: missions){
				mission.setStatusAlias();
				mission.setPriorityAlias();
			}
			
			request.setAttribute("missions", missions);
			request.getRequestDispatcher("/views/admin/listarMissoes.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}

}
