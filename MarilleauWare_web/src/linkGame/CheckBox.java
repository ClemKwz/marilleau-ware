package linkGame;

import gameEJB.CheckBoxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    
	private PrintWriter out;
	private static final int INIT = 1;
	private static final int GETRESPONSE = 2;
	private static final int ISENDGAME = 3;
	
	private ArrayList<int[]> randomParam;
	
	@EJB
	PartyController2Local pc;
	@EJB
	GameController2Local gc;
	@EJB
	CheckBoxController checkbox;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBox() {
        super();
        // TODO Auto-generated constructor stub
        randomParam = new ArrayList<int[]>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int action;
		int idPlayer = (int) request.getSession().getAttribute("idUser");
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame = pc.getIdGameByIdParty(idParty);
		int[] coordAndColor;
		
		response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		action = Integer.parseInt(request.getParameter("action"));

		System.out.println("idUser => " + idPlayer);

		// genere les parametres aleatoires si ce n'est pas deja fait pour la game courante
		if (randomParam.isEmpty() || !randomParamContainsIdGame(idGame)) {
			coordAndColor = createRandomTab(idGame);
		} else { // sinon on les recupere
			coordAndColor = getRandomTab(idGame);
		}
		
		switch(action){
		case INIT : 
			//gameManager.connect();
			String envoiParam = idPlayer + ";" + idParty + ";" + idGame + ";";
			for (int i=1; i < coordAndColor.length; i++) {
				envoiParam += coordAndColor[i] + ";";
			}
			out.print(envoiParam);
			break;
		case GETRESPONSE :
			int score = Integer.parseInt(request.getParameter("score"));
			int scorefinal = checkbox.calculScoreFinal(score);
			gc.addScore(idGame, idPlayer, scorefinal);
			
			break;
		case ISENDGAME :
			idPlayer = (int) request.getSession().getAttribute("idUser");
			
			idParty = pc.getIdPartyByIdUser(idPlayer);
			idGame = pc.getIdGameByIdParty(idParty);
			
			System.out.println("");
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
				}
				
				recupScore = recupScore + "</table> ";
				out.print("end_" + recupScore);
				
			}
			break;
		default :
			break;
		}
		out.close();
	}
	
	
	private boolean randomParamContainsIdGame(int idGame) {
		int i = 0;
		boolean found = false;
		
		while (!found || i < randomParam.size()) {
			found = randomParam.get(i)[0] == idGame;
			i++;
		}
		
		return found;
	}
	
	/*
	 * Genere un tableau d'entiers aleatoires correspondant a :
	 * 	- indice 0 : idGame
	 * 	- indices 1 a 20 : positions des checkbox
	 *  - indices 21 a 40 : couleurs des contours des checkbox
	 */
	private int[] createRandomTab(int idGame) {
		int possiblePositions = 20*15 + 1; // nombre de positions possibles pour les checkbox dans le canvasElem
		int nb_checkbox = 20;
		int sizeTab = 1 + 2 * nb_checkbox;
		int[] randTab = new int[sizeTab];
		
		randTab[0] = idGame;
		
		for (int i=1; i<sizeTab; i++) {
			randTab[i] = (int) (Math.random()*possiblePositions);
		}
		
		return randTab;
	}
	
	/*
	 * Retourne les parametres aleatoires deja utilises
	 * dans la game idGame
	 */
	private int[] getRandomTab(int idGame) {
		int i = 0;
		
		while ( i < randomParam.size() && randomParam.get(i)[0] != idGame) {
			i++;
		}
		
		return randomParam.get(i);
	}
	

}
