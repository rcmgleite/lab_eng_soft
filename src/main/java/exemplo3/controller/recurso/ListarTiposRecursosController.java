package exemplo3.controller.recurso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.ResourceType;

@WebServlet("/listarTiposDeRecursos")
public class ListarTiposRecursosController extends HttpServlet{

//	private RecursoDAO dao = new RecursoDAO();
	private GenericDAO dao = new GenericDAO();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7452215627284129825L;
	
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
			List<ResourceType> resources = dao.getList(ResourceType.class);
		
			request.setAttribute("resources", resources);
			request.getRequestDispatcher("/views/admin/listarTipoDeRecursos.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}

}
