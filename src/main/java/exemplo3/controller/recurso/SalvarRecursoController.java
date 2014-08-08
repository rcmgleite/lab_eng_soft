package exemplo3.controller.recurso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.RecursoDAO;
import exemplo3.model.Resource;
import exemplo3.model.ResourceType;

@WebServlet("/salvarRecurso")
public class SalvarRecursoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 435311885030051620L;

	private RecursoDAO dao = new RecursoDAO();

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
			String resourceTypeID = request.getParameter("selected_resourceType");

			Resource resource = new Resource();
			if (id != null && !id.equals("") ){
				resource.setId(Long.parseLong(id));
			}

			ResourceType rt = dao.getResourceTypeWhere("id = " + resourceTypeID);
			
			resource.setResourceType(rt);
			
			/*Para não perder a qual missão o recurso esta alocado mesmo quando mudarmos o tipo dele*/
			List<Resource> rs = dao.getResourcesWhere("id = " + id);
			if(rs != null && rs.size() != 0)
				resource.setMission(rs.get(0).getMission());
			
			dao.salvarRecurso(resource);

			request.setAttribute("msgSucesso", "Tipo de recurso salvo com sucesso!");
			request.getRequestDispatcher("/listarRecursos").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}
}
