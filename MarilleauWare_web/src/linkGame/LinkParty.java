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
		
		idPlayer = (int) request.getSession().getAttribute("idUser");
		
		idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame = pc.getIdGameByIdParty(idParty);
		
		System.out.println("");
		if (gc.containsNegativeScore(idGame, idPlayer)) {
			// Tout le monde n'a pas joue
			System.out.println("Tout le monde n'a pas joué");
			out.print("wait...");
		} else {//Tout le monde a joue
			System.out.println("Tout le monde a fini de joué");
			out.print("end_" + idPlayer + "; " + idParty + ";" + idGame);
			
		}
		
		
		out.close();
	}

}
