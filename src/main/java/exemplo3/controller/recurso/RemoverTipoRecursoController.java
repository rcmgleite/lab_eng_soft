package exemplo3.controller.recurso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.ResourceType;

@WebServlet("/removerTipoRecurso")
public class RemoverTipoRecursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverTipoRecursoController() {
	}

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
			Long pk = Long.parseLong(request.getParameter("id"));
			dao.remover(pk, ResourceType.class);
			
			request.setAttribute("msgSucesso", "Tipo de recurso removido com sucesso!");
			request.getRequestDispatcher("/listarTiposDeRecursos").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Erro ao deletar item selecionado. Ele está sendo usado"
					+ " por algum Recurso");
			request.getRequestDispatcher("/listarTiposDeRecursos")
			.forward(request, response);
		}
	}
}
