package exemplo3.controller.recurso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.GenericDAO;
import exemplo3.model.Resource;
import exemplo3.model.ResourceType;

@WebServlet("/detalharRecurso")
public class EditarRecursoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025578345214393412L;
	
//	private RecursoDAO dao = new RecursoDAO();
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
			Resource resource = dao.findByPrimaryKey(pk, Resource.class);

			request.setAttribute("resource", resource);
			
			List<ResourceType> resourceTypes = dao.getList(ResourceType.class);
			request.setAttribute("resourceTypes", resourceTypes);
			
			this.selectDispatcher(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				request.getRequestDispatcher("/views/admin/formularioRecurso.jsp").forward(request, response);
				break;
				
			case 1:
				request.setAttribute("role", "1");
				request.getRequestDispatcher("/views/coord/formularioRecurso.jsp").forward(request, response);
				break;
				
			default:
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				System.out.println("Erro");
				break;
			}
		}
		else{
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}
}
