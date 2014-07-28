package exemplo3.controller.missions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.RecursoDAO;
import exemplo3.model.Resource;

@WebServlet("/novaMissao")
public class NovaMissaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecursoDAO dao = new RecursoDAO();
	
	public NovaMissaoController () {
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
			String id_acidente = request.getParameter("id_acidente");
			request.setAttribute("id_acidente", id_acidente);
			
			List<Resource> resources = dao.getResourcesWhere("mission.id = null");
			
			request.setAttribute("resources", resources);
			request.getRequestDispatcher("/views/admin/formularioMissao.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
					.forward(request, response);

		}

	}
}
