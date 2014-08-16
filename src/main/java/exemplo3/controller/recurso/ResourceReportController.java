package exemplo3.controller.recurso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.dao.UtilsDAO;
import exemplo3.model.Accident;
import exemplo3.model.Resource;
import exemplo3.model.ResourceType;

@WebServlet("/relatorioRecursos")
public class ResourceReportController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715299530091293358L;

	private GenericDAO dao = new GenericDAO();
	
	public ResourceReportController() {
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
			
			String selected_resourceType = request.getParameter("selected_resourceType");
			if(selected_resourceType != null && !selected_resourceType.equals("")){
				parameters.put("resourceType.id", selected_resourceType);
			}

			String mission_id = request.getParameter("mission_id");
			if(mission_id != null && !mission_id.equals("")){
				parameters.put("mission.id", mission_id);
			}
			
			/**
			 * 	caso size == 0 está entrando na tela de seleção dos filtros
			 **/
			if(parameters.size() == 0){
				List<ResourceType> resourceTypes = dao.getList(ResourceType.class);
				request.setAttribute("resourceTypes", resourceTypes);
				
				this.selectDispatcher(request, response, false);
			}
			/**
			 * 	Caso contrário já deve devolver o relatório
			 **/
			else{
				String where = UtilsDAO.buildWhere(parameters, Accident.class);
				List<Resource> resources = this.dao.getListEntityWhere(where, Resource.class);
				request.setAttribute("resources", resources);
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
					request.getRequestDispatcher("/views/admin/relatorioRecurso.jsp").forward(request, response);
				else{
					request.getRequestDispatcher("/views/admin/listarRecursos.jsp").forward(request, response);
				}
				break;
	
			case 1:
				request.setAttribute("role", "1");
				if(!hasParameters)
					request.getRequestDispatcher("/views/coord/relatorioRecurso.jsp").forward(request, response);
				else
					request.getRequestDispatcher("/views/coord/listarRecursos.jsp").forward(request, response);
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