package exemplo3.controller.accidents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.AccidentDAO;

@WebServlet("/removerAcidente")
public class RemoverAcidenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverAcidenteController() {
	}

	private AccidentDAO dao = new AccidentDAO();

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
			/*veio na query-string na requesição*/
			Long pk = Long.parseLong(request.getParameter("id"));
			dao.remover(pk);
			
			request.setAttribute("msgSucesso", "Acidente removido com sucesso!");
			request.getRequestDispatcher("/listarAcidentes").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}
}
