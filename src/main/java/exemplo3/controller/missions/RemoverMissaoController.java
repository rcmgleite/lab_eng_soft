package exemplo3.controller.missions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.RemoveCascade;
import exemplo3.dao.GenericDAO;
import exemplo3.model.Mission;

@WebServlet("/removerMissao")
public class RemoverMissaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverMissaoController() {
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
			/*veio na query-string na requesição*/
			Long pk = Long.parseLong(request.getParameter("id"));

			/**
			 * 	Antes de remover a missão, desaloco seus recursos.
			 **/
			Mission  mission = dao.findByPrimaryKey(pk, Mission.class);
			RemoveCascade.deallocateResources(mission, dao);
			
			/**
			 *	Remove de fato a missao
			 **/
			dao.remover(pk, Mission.class);
			
			request.setAttribute("msgSucesso", "Missão removida com sucesso!");
			
			//Uso o outputstream do servlet para colocar o path para o retorno no 'data' do success do ajax
			ServletOutputStream out_s = response.getOutputStream();
			out_s.print("/svc/detalharAcidente?id=" + mission.getAccident().getId());
			out_s.flush();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/views/erro.jsp")
			.forward(request, response);
		}
	}
}
