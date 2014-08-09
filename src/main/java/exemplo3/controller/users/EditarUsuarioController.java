package exemplo3.controller.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import utils.ProjectEnums.UserRoles;
import exemplo3.dao.GenericDAO;
import exemplo3.model.User;

@WebServlet("/editarUsuario")
public class EditarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarUsuarioController() {
	}

//	private UserDAO dao = new UserDAO();
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
			User usuario = dao.findByPrimaryKey(pk, User.class);

			UserRoles[] roles = ProjectEnums.UserRoles.values();
			
			request.setAttribute("usuario", usuario);
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/views/admin/formularioUsuario.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}
}
