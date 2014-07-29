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

@WebServlet("/listarRecursos")
public class ListarRecrusosAlocadosController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5588740358425161817L;
	
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
			List<Resource> resources = dao.getResources();
		
			request.setAttribute("resources", resources);
			request.getRequestDispatcher("/views/admin/listarRecursos.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}
}
