package exemplo3.controller.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.UserDAO;
import exemplo3.model.User;

@WebServlet("/salvarUsuario")
public class SalvarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarUsuarioController() {
	}

	private UserDAO dao = new UserDAO();

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
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String role = request.getParameter("role");

			User usuario = new User();
			if (id != null && !id.equals("") ){
				usuario.setId(Long.parseLong(id));
			}

			usuario.setUsername(username);
			usuario.setRole(Long.parseLong(role));
			usuario.setPassword(password);

			dao.salvar(usuario);

			request.setAttribute("msgSucesso", "Usuario " + username
					+ " salvo com sucesso!");
			request.getRequestDispatcher("/listarUsuarios").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}
}
