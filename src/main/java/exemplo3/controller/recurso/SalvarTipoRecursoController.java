package exemplo3.controller.recurso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.RecursoDAO;
import exemplo3.model.ResourceType;

@WebServlet("/salvarTipoRecurso")
public class SalvarTipoRecursoController extends HttpServlet{

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
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String external = request.getParameter("external");

			ResourceType resource = new ResourceType();
			if (id != null && !id.equals("") ){
				resource.setId(Long.parseLong(id));
			}

			resource.setName(name);
			resource.setDescription(description);
			resource.setExternal(Boolean.parseBoolean(external));

			dao.salvarTipoRecurso(resource);

			request.setAttribute("msgSucesso", "Tipo de recurso salvo com sucesso!");
			request.getRequestDispatcher("/listarTiposDeRecursos").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}
}
