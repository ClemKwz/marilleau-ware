package linkGame;

import gameEJB.FindTheDotControllerLocal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.GameController2Local;
import controllerEJB.PartyController2Local;

/**
 * Servlet implementation class FindTheDot
 */
@WebServlet("/FindTheDot")
public class FindTheDot extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final int INIT = 1;
	private static final int GETRESPONSE = 2;
	private static final int ISENDGAME = 3;
	private PrintWriter out;

	@EJB
	PartyController2Local pc;
	@EJB
	GameController2Local gc;
	@EJB
	FindTheDotControllerLocal ftdc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTheDot() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		out.print("FindTheDot:get");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		
		int action = Integer.parseInt(request.getParameter("action"));
		int idPlayer = (int) request.getSession().getAttribute("idUser");
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame;
		
		switch(action){
			case INIT :
				
				idGame = pc.getIdGameByIdParty(idParty);
				String params = idPlayer + ";" + idParty + ";" + idGame + ";";
				
				String paramDBB = ftdc.getDataGame(idGame);
				if(paramDBB == null) {
					paramDBB = ftdc.generateDataGame(idGame);
				}
				params += paramDBB;
				System.out.println("Data de la game final : "+paramDBB);
				out.print(params);
				break;
			case GETRESPONSE :
				
				idGame = pc.getIdGameByIdParty(idParty);
				int x2 = Integer.parseInt(request.getParameter("x"));
				int y2 = Integer.parseInt(request.getParameter("y"));
				
				String paramBDD = ftdc.getDataGame(idGame);
				String[] tabstring = paramBDD.split(";");
				
				int[] tabParams = new int[5];
				for (int i = 0;i < tabstring.length;i++) {
					tabParams[i] = Integer.parseInt(tabstring[i]);
				}

				int xCalcul = 0;
				int yCalcul = 0;
				
				if (tabParams[3] == 1) {
					xCalcul = 400-tabParams[1];
				}
				if(tabParams[4] == 1) {
					yCalcul = 300-tabParams[2];
				}
				
				double distance = Math.sqrt((x2-xCalcul) * (x2-xCalcul) + (y2-yCalcul) * (y2-yCalcul));
				int scorefinal = ftdc.calculScoreFinal((int)distance);
				
				gc.addScore(idGame, idPlayer, scorefinal);
				pc.addScore(idParty, idPlayer, scorefinal);
				if (!gc.containsNegativeScore(idGame)) {
					System.out.println("FindTheDot : Increment du curent game");
					pc.incrementCurrentGame(idParty);
				}
				
				System.out.println("FindTheDot du game : "+idGame+", Distance du joueur "+idPlayer+" : " + distance);
				out.print(distance);
				break;
			case ISENDGAME :
				
				idPlayer = (int) request.getSession().getAttribute("idUser");
				idParty = pc.getIdPartyByIdUser(idPlayer);
				idGame = Integer.parseInt(request.getParameter("idGame"));
				
				// Tout le monde n'a pas joue
				if (gc.containsNegativeScore(idGame)) {
					System.out.println("Tout le monde n'a pas joué");
					
					out.print("wait...");
				//Tout le monde a joue
				} else {
					System.out.println("Tout le monde a fini de joué");
					
					//récupération des score
					TreeMap<String,Integer> listeScore =  gc.getAllScore(idGame);
					
					//Mise en place du tableau html contenant les scores
					int i = 1;
					String recupScore = "<table><tr><td>Rank</td><td>Pseudo</td><td>Score</td> ";
					for(String login : listeScore.keySet()){
						recupScore = recupScore +  "<tr>"+"<td>"+i +". </td><td>"+login+" : </td><td>"+ listeScore.get(login)+ "</td><tr>";
						i++;
					}
					
					recupScore = recupScore + "</table> ";
					out.print("end_" + recupScore);
				}
				break;
			default :
				out.print("default action error");
				break;
		}
		out.close();
	}
}