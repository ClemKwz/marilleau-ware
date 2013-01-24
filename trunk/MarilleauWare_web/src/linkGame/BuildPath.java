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

import java.lang.Math;
import java.util.TreeMap;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/BuildPath")
public class BuildPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrintWriter out;
	private static final int INIT = 1;
	private static final int GETRESPONSE = 2;
	private static final int ISENDGAME = 3;

	@EJB
	PartyController2Local pc;
	@EJB
	GameController2Local gc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildPath() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		
		int x,y;
		x = 200;
		y = 300;
		
		
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
		//System.out.println("idUser => " + idPlayer);
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame = pc.getIdGameByIdParty(idParty);
		
		//System.out.println("Donnée recu de l'user : " + idPlayer);
		
		switch(action){
		case INIT : 
			String s = "" + idPlayer + ";" + idParty + ";" + idGame + ";";
			int nbPath = (int) (Math.random()*6);
			nbPath += 6;
			int neg;
			int posX=0;
			int posY=0;
			int ran;
			for(int i=0; i<nbPath; i++){
				do{
					ran = ((int)(Math.random()*50))+20;
					neg = ((Math.random()>0.65 && i>3)?1:0);
					if(neg==1)
						ran = -ran;
					System.out.println("ran : " + ran + "   posX : " + posX + "   posY : " + posY);
				}while((posX+ran)>380||(posX+ran)<20||(posY+ran)>280||(posY+ran)<20);
				posX += ran;
				posY += ran;
				s += ran + ";";
			}

			//System.out.println(s);
			out.print(s);
			break;
			// Pas besoin de reponse, l'utilisateur a son temps, il sait deja s'il a perdu, ilattend juste le tableau des resultats
		case GETRESPONSE :
			int sec = Integer.parseInt(request.getParameter("sec"));
			int msec = Integer.parseInt(request.getParameter("msec"));
			int nbPoint;
			if(sec==-1 || msec==-1){
				out.print("Nombre de points : 0");
				nbPoint = 0;
			}
			else{
				nbPoint = (200000-(10000*sec+100*msec));
				out.print("Nombre de points : " + nbPoint);
			}
			System.out.println("score : " + nbPoint);
			gc.addScore(idGame, idPlayer, nbPoint);
			if (!gc.containsNegativeScore(idGame)) {
				pc.incrementCurrentGame(idParty);
			}
			break;
		case ISENDGAME :
			idPlayer = (int) request.getSession().getAttribute("idUser");
			
			idParty = pc.getIdPartyByIdUser(idPlayer);
			idGame = Integer.parseInt(request.getParameter("idGame"));
			//idGame = pc.getIdGameByIdParty(idParty);
			
			//System.out.println("BuildPath : idGame "+idGame);
			if (gc.containsNegativeScore(idGame)) {// Tout le monde n'a pas joue
				out.print("wait...");
			} else {//Tout le monde a joue
				//récupération des score
				//TreeMap<String,Integer> listeScore =  gc.getAllScore(idGame);
				//Mise en place du tableau html contenant les scores
				
				TreeMap<String,Integer> listeScore = new TreeMap<String,Integer>();
				listeScore.put("LePNJ", 500);
				listeScore.put("Fabien", 500);
				listeScore.put("Paul", 480);
				listeScore.put("Clément", 200);
				listeScore.put("Pierre", 0);
				
				String recupScore;
				int i =1;
				recupScore = "<table><tr><td>Rank</td><td>Pseudo</td><td>Score</td> ";
				for(String login : listeScore.keySet()){
					recupScore = recupScore +  "<tr>"+
										"<td>"+i +". </td><td>"+login+" : </td><td>"+ listeScore.get(login)+ "</td><tr>";
					i++;
				}
				recupScore = recupScore + "</table> ";
				out.print("end_" + recupScore);
				
			}
			break;
		default :
			break;
		}
		//System.out.println("x => " + x);
		out.close();
		
	}

}
;;