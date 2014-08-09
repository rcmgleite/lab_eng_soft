package exemplo3.controller.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.User;

@WebServlet("/listarUsuarios")
public class ListarUsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarUsuariosController() {
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
			List<User> usuarios = dao.getList(User.class);
			
			for(User user: usuarios){
				user.setRoleAlias();
			}
			
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("/views/admin/listarUsuarios.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}
}
