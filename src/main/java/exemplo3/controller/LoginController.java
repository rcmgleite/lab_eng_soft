package exemplo3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SessionManager;
import exemplo3.dao.UserDAO;
import exemplo3.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
	}

	private UserDAO dao = new UserDAO();

	private SessionManager sm = new SessionManager(60 * 30);
	
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
		
		Boolean hasSession = sm.verifySession(request, response);
		if(hasSession){
			this.selectDispatcher(request, response, sm.getRole(request));
		}else{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = dao.getUserByUnamePassword(username, password);
			
			if(user != null){
				Integer role = Integer.parseInt(user.getRole().toString());
				sm.createSession(request, response, role);
				this.selectDispatcher(request, response, role);
			}
			else{
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}	
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response, Integer role) throws ServletException, IOException{
		switch (role) {
		case 0:
			request.setAttribute("role", "0");
			request.getRequestDispatcher("/views/admin/index_admin.jsp").forward(request, response);
			break;
		
		case 1:
			request.setAttribute("role", "1");
			request.getRequestDispatcher("/views/coord/index_coord.jsp").forward(request, response);
			break;
			
		case 2:
			request.setAttribute("role", "2");
			request.getRequestDispatcher("/views/espec/index_espec.jsp").forward(request, response);
			break;
			
		case 3:
			request.setAttribute("role", "3");
			request.getRequestDispatcher("/views/chefe_missao/index_chefe_missao.jsp").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			System.out.println("Erro");
			break;
		}
	}
}
