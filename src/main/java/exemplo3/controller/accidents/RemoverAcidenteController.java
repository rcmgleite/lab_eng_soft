package exemplo3.controller.accidents;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.RemoveCascade;
import exemplo3.dao.AccidentDAO;
import exemplo3.dao.MissionDAO;
import exemplo3.dao.RecursoDAO;
import exemplo3.model.Accident;
import exemplo3.model.Mission;

@WebServlet("/removerAcidente")
public class RemoverAcidenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverAcidenteController() {
	}

	private AccidentDAO dao = new AccidentDAO();
	private MissionDAO mDao = new MissionDAO();
	private RecursoDAO rDao = new RecursoDAO();

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
			/*veio na query-string na requesição*/
			Long pk = Long.parseLong(request.getParameter("id"));
			
			Accident accident = dao.findByPrimaryKey(pk);
			List<Mission> missions = accident.getMissions();
			for(Mission mission: missions){
				
				/** 	
				 * 	Primeiro desaloca os recursos para todas as missões dentro do acidente
				 **/
				RemoveCascade.deallocateResources(mission, mDao, rDao);
				
				/**
				 *  Depois Deleta cada missão
				 **/
				mDao.remover(mission.getId());
			}
			/**
			 *		Por fim, deleto o acidente.
			 **/
			dao.remover(pk);
			
			request.setAttribute("msgSucesso", "Acidente removido com sucesso!");
			request.getRequestDispatcher("/listarAcidentes").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
}
