package exemplo3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class logoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3349432872715194960L;

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

		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}
}