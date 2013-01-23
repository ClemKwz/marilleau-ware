package linkGame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.GameController2Local;
import controllerEJB.PartyController2Local;

/**
 * Servlet implementation class LinkParty
 */
@WebServlet("/LinkParty")
public class LinkParty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int idPlayer;
	int idParty;
	@EJB
	PartyController2Local pc;

	@EJB
	GameController2Local gc;

	private PrintWriter out;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkParty() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		out.print("sucemesdboules 2 fois");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		
		//récupération de l'id du game suivant
		int idGame = Integer.parseInt(request.getParameter("idGame"));
		int idGameSuiv = gc.getNextgame(idGame);
		
		//récupération du type de jeu du game suivant;
		gc.getNameGameDesc(idGameSuiv);
		out.print("idGameSuiv");
		
		out.close();
	}

}
