package exemplo3.controller.accidents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums.AccidentType;
import exemplo3.dao.AccidentDAO;
import exemplo3.model.Accident;
import exemplo3.model.Mission;

@WebServlet("/detalharAcidente")
public class EditarAcidenteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025578345214393412L;
	
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
			/**
			 * 	Veio como parâmetro na URL da requesição
			 **/
			Long pk = Long.parseLong(request.getParameter("id"));
			Accident accident = dao.findByPrimaryKey(pk);
			
			for(Mission mission: accident.getMissions()){
				mission.setPriorityAlias();
				mission.setStatusAlias();
			}

			request.setAttribute("acidente", accident);
			
			AccidentType[] ac_types = AccidentType.values();
			request.setAttribute("ac_types", ac_types);
			
			request.getRequestDispatcher("/views/admin/formularioAcidente.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);

		}

	}

}

