package linkGame;

import gameEJB.FindTheDotControllerLocal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

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
	private ArrayList<int[]> randomParam = new ArrayList<int[]>(); 

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
		double distance;
				
		int idPlayer = (int) request.getSession().getAttribute("idUser");
		System.out.println("idUser => " + idPlayer);
		
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame;
		
		
		System.out.println("Donnée recu de l'user : " + idPlayer);
		
		switch(action){
		case INIT : 
			//gameManager.connect();
			idGame = pc.getIdGameByIdParty(idParty);
			String params = "";
			params = idPlayer + ";" + idParty + ";" + idGame + ";";
			
			// TODO
			String paramDBB = ftdc.getDataGame(idGame);
			if(paramDBB == null) {
				paramDBB = ftdc.generateDataGame(idGame);
			}
			params += paramDBB;
			out.print(params);
			
			/*
			int[] tab = new int[5];
			boolean find = false;
			if(randomParam.size() == 0)
			{
				x1 = (int) (Math.random()*400);
				y1 = (int) (Math.random()*300);

				int xInverted = (int) ((Math.random()>0.5)?0:1);
				int yInverted = (int) ((Math.random()>0.5)?0:1);
				tab[0] = idGame;
				tab[1] = x1;
				tab[2] = y1;
				tab[3] = xInverted;
				tab[4] = yInverted;
				randomParam.add(tab);
			}
			else
			{
				for(int i = 0;i < randomParam.size();i++)
				{
					int[] tabTmp = randomParam.get(i);
					if(tabTmp[0] == idGame)
					{
						tab = tabTmp;
						find = true;
					}
				}
				if(!find)
				{
					x1 = (int) (Math.random()*400);
					y1 = (int) (Math.random()*300);
					
					int xInverted = (int) ((Math.random()>=0.5)?0:1);
					int yInverted = (int) ((Math.random()>=0.5)?0:1);
					System.out.println("x => " + xInverted  + "\n" + "y => " + yInverted);
					tab[0] = idGame;
					tab[1] = x1;
					tab[2] = y1;
					tab[3] = xInverted;
					tab[4] = yInverted;
					randomParam.add(tab);
				}
			}
			
			out.print(idPlayer + ";" + idParty + ";" + idGame + ";" + tab[1] + ";" + tab[2] + ";" + tab[3] + ";" + tab[4]);*/
			break;
		case GETRESPONSE :
			idGame = pc.getIdGameByIdParty(idParty);
			int x2 = Integer.parseInt(request.getParameter("x"));
			int y2 = Integer.parseInt(request.getParameter("y"));
			int xCalcul = x1;
			int yCalcul = y1;
			
			String paramBDD = ftdc.getDataGame(idGame);
			String[] tabstring = paramBDD.split(";");
			
			int[] tabParams = new int[5];
			for(int i = 0;i < tabstring.length;i++)
			{
				tabParams[i] = Integer.parseInt(tabstring[i]);
			}
			if(tabParams[3] == 1)
				xCalcul = 400-x1;
			if(tabParams[4] == 1)
				yCalcul = 300-y1;
			
			distance = Math.sqrt((x2-xCalcul) * (x2-xCalcul) + (y2-yCalcul) * (y2-yCalcul));
			//d = (double)Integer.parseInt(request.getParameter("distance"));
			
			out.print(distance);
			System.out.println("distance : "+ distance);
			//System.out.println("Distance entre les points : " + d + " pixels");
			
			int scorefinal = ftdc.calculScoreFinal((int)distance);
			//gameManager.play(request.getSession().getAttribute("sessionID"), doubl);
			
			gc.addScore(idGame, idPlayer, scorefinal);
			pc.addScore(idParty, idPlayer, scorefinal);
			if (!gc.containsNegativeScore(idGame)) {
				pc.incrementCurrentGame(idParty);
			}
			break;
		case ISENDGAME :
			idPlayer = (int) request.getSession().getAttribute("idUser");
			
			idParty = pc.getIdPartyByIdUser(idPlayer);
			idGame = Integer.parseInt(request.getParameter("idGame"));
			//idGame = pc.getIdGameByIdParty(idParty);
			
			System.out.println("idGame "+idGame);
			if (gc.containsNegativeScore(idGame)) {
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
					i++;
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
		//out.close();
	}

}
