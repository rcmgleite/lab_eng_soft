package exemplo3.controller.recurso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.Resource;

@WebServlet("/removerRecurso")
public class RemoverRecursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverRecursoController() {
	}

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
			dao.remover(pk, Resource.class);
			
			request.setAttribute("msgSucesso", "Recurso removido com sucesso!");
			
			//Uso o outputstream do servlet para colocar o path para o retorno no 'data' do success do ajax
			ServletOutputStream out_s = response.getOutputStream();
			out_s.print("/svc/listarRecursos");
			out_s.flush();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Erro ao deletar item selecionado. Ele está sendo usado"
					+ " por algum Recurso");
			request.getRequestDispatcher("/listarTiposDeRecursos")
			.forward(request, response);
		}
	}
}
