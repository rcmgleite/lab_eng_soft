package exemplo3.controller.recurso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.ResourceType;

@WebServlet("/detalharTipoRecurso")
public class EditarTipoRecursoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025578345214393412L;
	
//	private RecursoDAO dao = new RecursoDAO();
	private GenericDAO dao = new GenericDAO();

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
			 * 	Veio como parâmetro na URL da requesição
			 **/
			Long pk = Long.parseLong(request.getParameter("id"));
			ResourceType rt = dao.findByPrimaryKey(pk, ResourceType.class);

			request.setAttribute("resource", rt);
			request.getRequestDispatcher("/views/admin/formularioTipoRecurso.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}

}
