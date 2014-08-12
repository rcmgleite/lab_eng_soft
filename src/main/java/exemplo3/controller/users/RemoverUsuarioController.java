package exemplo3.controller.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.RemoveCascade;
import exemplo3.dao.GenericDAO;
import exemplo3.model.User;

@WebServlet("/removerUsuario")
public class RemoverUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverUsuarioController() {
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
			User user = dao.findByPrimaryKey(pk, User.class);
			/**
			 *	Antes de remover usuário, caso ele seja um chefe de missão, precisa desvincular
			 *	as missões do mesmo. 
			 **/
			RemoveCascade.deallocateMissionsFromUser(user, dao);
			
			/**
			 * 	No final pode-se remover o usuário sem problemas.
			 **/
			dao.remover(pk, User.class);
			
			request.setAttribute("msgSucesso", "Usuario removido com sucesso!");
			
			//Uso o outputstream do servlet para colocar o path para o retorno no 'data' do success do ajax
			ServletOutputStream out_s = response.getOutputStream();
			out_s.print("/svc/listarUsuarios?msgSucesso='Dado deletado com sucesso!'");
			out_s.flush();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}
}
