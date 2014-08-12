package exemplo3.controller.users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import utils.ProjectEnums.UserRoles;
import exemplo3.dao.GenericDAO;
import exemplo3.dao.UtilsDAO;
import exemplo3.model.User;

@WebServlet("/salvarUsuario")
public class SalvarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarUsuarioController() {
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
			
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String new_role = request.getParameter("selected_role");
			
			//Primeiro verifico se o username já existe...não podem existir 2 iguais...
			Map<String, String> params = new HashMap<String, String>();
			params.put("username", username);
			String where = UtilsDAO.buildWhere(params, User.class);
			
			UserRoles[] roles = ProjectEnums.UserRoles.values();
			request.setAttribute("roles", roles);
			
			List<User> users = dao.getListEntityWhere(where, User.class);
			/**
			 * 	Verifica se o username já não foi utilizado
			 **/
			if(users != null && users.size() != 0 && (id == null || id.equals(""))){
				request.setAttribute("errorMsg", "Usuário já utilizado");
				request.getRequestDispatcher("/views/admin/formularioUsuario.jsp").forward(request,
						response);
			}
			else {
				User usuario = new User();
				if (id != null && !id.equals("") ){
					usuario.setId(Long.parseLong(id));
				}
				if(username.equals("") || password.equals("")){
					request.setAttribute("errorMsg", "Não deixe campos em branco");
					request.getRequestDispatcher("/views/admin/formularioUsuario.jsp").forward(request,
							response);
				}
				else{
					usuario.setUsername(username);
					Integer role_value = ProjectEnums.UserRoles.valueOf(new_role).ordinal();
					usuario.setRole(Long.parseLong(role_value.toString()));
					usuario.setPassword(password);
		
					dao.salvar(usuario, User.class);
		
					request.setAttribute("msgSucesso", "Usuario " + username
							+ " salvo com sucesso!");
					request.getRequestDispatcher("/listarUsuarios").forward(request,
							response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Preencha corretamente os campos do formulário");
			request.getRequestDispatcher("/views/admin/formularioUsuario.jsp").forward(request,
					response);
		}

	}
}
