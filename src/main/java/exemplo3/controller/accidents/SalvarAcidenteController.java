package exemplo3.controller.accidents;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ProjectEnums;
import exemplo3.dao.AccidentDAO;
import exemplo3.model.Accident;

@WebServlet("/salvarAcidente")
public class SalvarAcidenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarAcidenteController() {
	}

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
			String id = request.getParameter("id");
			String strDate = request.getParameter("_date");
			String location = request.getParameter("_localizacao");
			String numVictims = request.getParameter("_nVitimas");
			String description = request.getParameter("_descricao");
			String type = request.getParameter("selected_type");
			String status = request.getParameter("_status");
			
			Accident accident = new Accident();
			if (id != null && !id.equals("") ){
				accident.setId(Long.parseLong(id));
			}
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formater.parse(strDate);
			accident.setDate(date);
			accident.setLocation(location);
			accident.setNumVictims(Long.parseLong(numVictims));
			accident.setDescription(description);
			
			Integer type_value = ProjectEnums.AccidentType.valueOf(type).ordinal();
			accident.setType(Long.parseLong(type_value.toString()));
			if(status != null && status != "")
				accident.setStatus(Long.parseLong(status));
			else
				accident.setStatus(0L); //status em aberto
				
			dao.salvar(accident);

			request.setAttribute("msgSucesso", "Acidente salvo com sucesso!");
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

