package exemplo3.controller.accidents;

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
import utils.ProjectEnums.AccidentStatus;
import utils.ProjectEnums.AccidentType;
import exemplo3.dao.GenericDAO;
import exemplo3.dao.UtilsDAO;
import exemplo3.model.Accident;

@WebServlet("/relatorioAcidentes")
public class AccidentReportController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715299530091293358L;

	private GenericDAO dao = new GenericDAO();
	
	public AccidentReportController() {
	}

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
			 * 	O mapa de parâmetros deve conter como key o nome do campo na classe do model
			 **/
			Map<String, String> parameters = new HashMap<String, String>();
			String initStrDate = request.getParameter("_initDate");
			if(initStrDate != null && !initStrDate.equals("")){
				parameters.put("initial_date", initStrDate);
			}
			
			String finalStrDate = request.getParameter("_finalDate");
			if(finalStrDate != null && !finalStrDate.equals("")){
				parameters.put("final_date", finalStrDate);
			}
			
			String numVictims = request.getParameter("_nVitimas");
			if(numVictims != null && !numVictims.equals("")){
				parameters.put("numVictims", numVictims);
			}
			
			String type = request.getParameter("selected_type");
			if(type != null && !type.equals("")){
				Integer i_type = ProjectEnums.AccidentType.valueOf(type).ordinal();
				parameters.put("type", i_type.toString());
			}
			
			String status = request.getParameter("selected_status");
			if(status != null && ! status.equals("")){
				Integer i_status = ProjectEnums.AccidentStatus.valueOf(status).ordinal();
				parameters.put("status", i_status.toString());
			}
			
			/**
			 * 	caso size == 0 está entrando na tela de seleção dos filtros
			 **/
			if(parameters.size() == 0){
				AccidentType[] ac_types = AccidentType.values();
				request.setAttribute("ac_types", ac_types);
				
				AccidentStatus[] ac_status = AccidentStatus.values();
				request.setAttribute("ac_status", ac_status);
				
				this.selectDispatcher(request, response, false);
			}
			/**
			 * 	Caso contrário já deve devolver o relatório
			 **/
			else{
				String where = UtilsDAO.buildWhere(parameters);
				List<Accident> accidents = this.dao.getListEntityWhere(where, Accident.class);
				for(Accident accident: accidents){
					/*
					 *	Seta os atributos typeAlias e statusAlias
					 */
					accident.setTypeAlias();
					accident.setStatusAlias();
				}
				request.setAttribute("accidents", accidents);
				this.selectDispatcher(request, response, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
					.forward(request, response);
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response, Boolean hasParameters) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				if(!hasParameters)
					request.getRequestDispatcher("/views/admin/relatorioAcidente.jsp").forward(request, response);
				else{
					request.getRequestDispatcher("/views/admin/listarAcidentes.jsp").forward(request, response);
				}
				break;
	
			case 1:
				request.setAttribute("role", "1");
				if(hasParameters)
					request.getRequestDispatcher("/views/coord/relatorioAcidente.jsp").forward(request, response);
				else
					request.getRequestDispatcher("/views/coord/listarAcidentes.jsp").forward(request, response);
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