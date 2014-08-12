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
import exemplo3.dao.GenericDAO;
import exemplo3.model.Accident;

@WebServlet("/salvarAcidente")
public class SalvarAcidenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarAcidenteController() {
	}

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
			String strDate = request.getParameter("_date");
			String location = request.getParameter("_localizacao");
			String numVictims = request.getParameter("_nVitimas");
			String description = request.getParameter("_descricao");
			String type = request.getParameter("selected_type");
			String status = request.getParameter("_status");
			String urlCMS = request.getParameter("_urlCMS");
			
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
			
			/**
			 *	Antes de salvar a URL do SVC, temos que fazer o parse dela..
			 *	exemplo:
			 *		url do youtube: https://www.youtube.com/watch?v=HKFDYdaSyng
			 *		url final:			  //www.youtube.com/embed/HKFDYdaSyng 
			 **/
			String finalSVCUrl = parseSVCUrl(urlCMS);
			accident.setUrlCMS(finalSVCUrl);
			
			Integer type_value = ProjectEnums.AccidentType.valueOf(type).ordinal();
			accident.setType(Long.parseLong(type_value.toString()));
			if(status != null && status != "")
				accident.setStatus(Long.parseLong(status));
			else
				accident.setStatus(0L); //status em aberto
				
			dao.salvar(accident, Accident.class);

			request.setAttribute("msgSucesso", "Acidente salvo com sucesso!");
			request.getRequestDispatcher("/listarAcidentes").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			//campos não validados... deve voltar para o formuário
			request.setAttribute("errorMsg", "Verifique se os campos foram preenchidos corretamente.");
			this.selectDispatcher(request, response);
		}
	}
	
	private void selectDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strRole = request.getSession().getAttribute("role").toString();
		if(strRole != null && strRole != ""){
			Integer role = Integer.parseInt(strRole);
			switch (role) {
			case 0:
				request.setAttribute("role", "0");
				request.getRequestDispatcher("/novoAcidente").forward(request, response);
				break;
	
			case 1:
				request.setAttribute("role", "1");
				request.getRequestDispatcher("/novoAcidente").forward(request, response);
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
	
	/**
	 *		url de entrada:		  https://www.youtube.com/watch?v=HKFDYdaSyng
	 *		url final:			  //www.youtube.com/embed/HKFDYdaSyng 
	 **/
	private static String parseSVCUrl(String rawUrl){
		String finalUrl;
		
		/**
		 * 	Primeiro retiro o https: caso ele exista
		 **/
		Integer initial_index = rawUrl.indexOf("https:");
		if(initial_index != -1){
			finalUrl = rawUrl.substring(initial_index + "https:".length());
		}
		else{
			finalUrl = rawUrl;
		}
		/**	
		 * 	substitui 'watch?v=' por embed/ 
		 **/
		finalUrl = finalUrl.replace("watch?v=", "embed/");
		return finalUrl;
	}
}

