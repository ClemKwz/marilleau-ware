package linkGame;

import gameEJB.CheckBoxControllerLocal;

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
 * Servlet implementation class CheckBox
 */
@WebServlet("/CheckBox")
public class CheckBox extends HttpServlet {

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
	CheckBoxControllerLocal cbc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBox() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		out.print("CheckBox:get");
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
				
				String paramDBB = cbc.getDataGame(idGame);
				if(paramDBB == null) {
					paramDBB = cbc.generateDataGame(idGame);
				}
				params += paramDBB;
				System.out.println("Data de la game final : "+paramDBB);
				out.print(params);
				break;
			case GETRESPONSE :
				
				idGame = Integer.parseInt(request.getParameter("idGame"));
				int score = Integer.parseInt(request.getParameter("score"));
				int scorefinal = cbc.calculScoreFinal(score);
				
				gc.addScore(idGame, idPlayer, scorefinal);
				pc.addScore(idParty, idPlayer, scorefinal);
				if (!gc.containsNegativeScore(idGame)) {
					System.out.println("CheckBox : Increment du curent game");
					pc.incrementCurrentGame(idParty);
				}
				
				System.out.println("CheckBox du game : "+idGame+", Score du joueur "+idPlayer+" : " + score);
				out.print(scorefinal);
				break;
			case ISENDGAME :
			
				idGame = Integer.parseInt(request.getParameter("idGame"));

				// Tout le monde n'a pas joue
				if (gc.containsNegativeScore(idGame)) {
					System.out.println("CheckBox de idGame "+idGame+" : Tout le monde n'a pas joué");
					
					out.print("wait...");
				//Tout le monde a joue
				} else {
					System.out.println("CheckBox de idGame "+idGame+" : Tout le monde a fini de joué");
					
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
