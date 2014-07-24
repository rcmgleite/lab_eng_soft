package exemplo3.controller.accidents;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo3.dao.AccidentDAO;
import exemplo3.model.Accident;

@WebServlet("/listarAcidentes")
public class ListarAcidentesController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1160257872638561767L;

	private AccidentDAO dao = new AccidentDAO();
	
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
			List<Accident> accidents = dao.getAccidents();
			
			for(Accident accident: accidents){
				/*
				 *	Seta os atributos typeAlias e statusAlias
				 */
				accident.setTypeAlias();
				accident.setStatusAlias();
			}
			
			request.setAttribute("accidents", accidents);
			request.getRequestDispatcher("/views/admin/listarAcidentes.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}
	}
}
