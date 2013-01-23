package linkGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.GameController2;
import controllerEJB.GameController2Local;
import controllerEJB.PartyController2Local;




/**
 * Servlet implementation class FindTheDot
 */
@WebServlet("/FindTheDot")
public class FindTheDot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PrintWriter out;
	private static final int INIT = 1;
	private static final int GETRESPONSE = 2;
	private static final int ISENDGAME = 3;
	private int x;
	private int x1, y1;
	

	@EJB
	PartyController2Local pc;
	@EJB
	GameController2Local gc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTheDot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		out = response.getWriter();
		out.print("sucemesdboules");
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
		double d;
		
		x1 = (int) (Math.random()*400);
		y1 = (int) (Math.random()*300);
		
		int xInverted = 0;
		int yInverted = 0;
		
		int idPlayer = (int) request.getSession().getAttribute("idUser");
		System.out.println("idUser => " + idPlayer);
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame = pc.getIdGameByIdParty(idParty);
		
		System.out.println("Donnée recu de l'user : " + idPlayer);
		
		switch(action){
		case INIT : 
			//gameManager.connect();
			out.print(idPlayer + ";" + idParty + ";" + idGame + ";" + x1 + ";" + y1 + ";" + xInverted + ";" + yInverted);
			break;
		case GETRESPONSE :
			int x2 = Integer.parseInt(request.getParameter("x"));
			int y2 = Integer.parseInt(request.getParameter("y"));
			d = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
			//d = (double)Integer.parseInt(request.getParameter("distance"));
			
			out.print(d);
			System.out.println("distance : "+ d);
			//System.out.println("Distance entre les points : " + d + " pixels");
			int doubl = (int) d;
			
			//gameManager.play(request.getSession().getAttribute("sessionID"), doubl);
			gc.addScore(idGame, idPlayer, doubl);
			
			break;
		case ISENDGAME :
			idPlayer = (int) request.getSession().getAttribute("idUser");
			
			idParty = pc.getIdPartyByIdUser(idPlayer);
			idGame = pc.getIdGameByIdParty(idParty);
			
			System.out.println("");
			if (gc.containsNegativeScore(idGame, idPlayer)) {
				// Tout le monde n'a pas joue
				System.out.println("Tout le monde n'a pas joué");
				out.print("wait...");
			} else {//Tout le monde a joue
				System.out.println("Tout le monde a fini de joué");
				
				//récupération des score
				TreeMap<String,Integer> listeScore =  gc.getAllScore(idGame);
				/*TreeMap<String,Integer> listeScore= new TreeMap<String,Integer>();
				listeScore.put("LePNJ", 500);
				listeScore.put("Fabien", 500);
				listeScore.put("Paul", 480);
				listeScore.put("Clément", 200);
				listeScore.put("Pierre", 0);*/
				
				
				//Mise en place du tableau html contenant les scores
				String recupScore;
				int i =1;
				recupScore = "<table><tr><td>Rank</td><td>Pseudo</td><td>Score</td> ";
				for(String login : listeScore.keySet()){
					recupScore = recupScore +  "<tr>"+
										"<td>"+i +". </td><td>"+login+" : </td><td>"+ listeScore.get(login)+ "</td><tr>";
				}
				
				recupScore = recupScore + "</table> ";
				out.print("end_" + recupScore);
				
			}
			break;
		default :
			break;
		}
		x++;
		//System.out.println("x => " + x);
		out.close();
	}

}
