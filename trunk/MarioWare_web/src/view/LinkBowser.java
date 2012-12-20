package view;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.*;


/**
 * Servlet implementation class AddTamere
 */
public class LinkBowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BowserControllerLocal bowser;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkBowser() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	list.addOneTest(Integer.getInteger(request.getParameter("val")));
		bowser.addParty(1, "PartyController");
		
		String s = bowser.affiche();
		//int i = list.showTest(1);
		//String s = "HAHA"+i;
		response.getWriter().print(s);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
