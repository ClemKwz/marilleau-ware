package linkGame;

import gameEJB.BuildPathControllerLocal;

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

import java.util.TreeMap;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/BuildPath")
public class BuildPath extends HttpServlet {
	
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
	BuildPathControllerLocal bpc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildPath() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		out.print("BuildPath:get");
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
				
				String paramDBB = bpc.getDataGame(idGame);
				if(paramDBB == null) {
					paramDBB = bpc.generateDataGame(idGame);
				}
				params += paramDBB;
				System.out.println("Data de la game final : "+paramDBB);
				out.print(params);
				break;
			case GETRESPONSE :
				
				idGame = pc.getIdGameByIdParty(idParty);
				int sec = Integer.parseInt(request.getParameter("sec"));
				int msec = Integer.parseInt(request.getParameter("msec"));
				
				int score = bpc.calculScoreFinal(sec, msec);
				
				gc.addScore(idGame, idPlayer, score);
				pc.addScore(idParty, idPlayer, score);
				if (!gc.containsNegativeScore(idGame)) {
					System.out.println("BuildPath : Increment du curent game");
					pc.incrementCurrentGame(idParty);
				}
				
				System.out.println("BuildPath du game : "+idGame+", Score du joueur "+idPlayer+" : " + score);
				out.print(score);
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