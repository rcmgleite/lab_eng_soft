package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManager {
	
	private Integer timeToLive = 60;
	
	public SessionManager(Integer timeToLive){
		this.timeToLive = timeToLive;
	}
	
	public Boolean verifySession(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Object username = session.getAttribute("user");
		if(username != null){
			refreshSession(request);
			return true;
		}
		return false;
	}
	
	public void createSession(HttpServletRequest request, HttpServletResponse response, Integer role){
		HttpSession session = request.getSession();
		session.setAttribute("user", (request.getParameter("username")));
		session.setAttribute("role", role);
		session.setMaxInactiveInterval(this.timeToLive);
	}
	
	public Integer getRole(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session != null){
			Object role = session.getAttribute("role");
			if(role != null)
				return Integer.parseInt(role.toString());
		}
		return -1;
	}
	
	private void refreshSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(this.timeToLive);
	}
}
