package marioware;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllerEJB.GameController2Local;
import controllerEJB.PartyController2Local;

import managerDB.PartyManagerLocal;
import managerDB.UserManagerLocal;
import model.Party;

/**
 * Servlet implementation class PartyCreation
 */
public class PartyCreation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	@EJB
	PartyManagerLocal pm;
	@EJB
	UserManagerLocal um;
	@EJB
	GameController2Local gc;
	@EJB
	PartyController2Local pc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyCreation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.print("Get on PartyCreation");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if (session.getAttribute("sessionID")==null) {
			String message = "Error : Your session is terminated";
			getServletContext().getRequestDispatcher("/index.jsp?message="+message).forward(request,response);
			return;
		}
		
		String sessionID = session.getAttribute("sessionID").toString();
		if(!sessionID.equals(session.getId())) {
			String message = "Error : Your session ID doesn't exist";
			getServletContext().getRequestDispatcher("index.jsp?message="+message).forward(request,response);
			return;
		}

		int idUser = Integer.parseInt(session.getAttribute("idUser").toString());
		
		// Recuperation du champ nom de la partie
		String nameParty = request.getParameter("nameParty").trim();
		// Recuperation du champ description de la partie
		String descriptionParty = request.getParameter("descriptionParty").trim();
		
		// Verification nameParty
		if (nameParty == null || nameParty.equals("")) {
			String message = "Name is incorrect !";
			getServletContext().getRequestDispatcher("/partyCreation.jsp?message="+message).forward(request,response);
			return;
		}
		
		// Verification descriptionParty
		if (descriptionParty == null || descriptionParty.equals("")) {
			String message = "Description is incorrect !";
			getServletContext().getRequestDispatcher("/partyCreation.jsp?message="+message).forward(request,response);
			return;
		}
		
		// Insertion BDD
		// Ajout de la nouvelle partie
		if(pm.getIdPartyByName(nameParty) != -1){
			String message = "Name of party already used";
			getServletContext().getRequestDispatcher("/partyCreation.jsp?message="+message).forward(request,response);
			return;
		}
		
		pm.createPartie(nameParty,descriptionParty,idUser);
		int idParty = pm.getIdPartyByName(nameParty);
		
		// Creation des games
		Party party = pm.getPartyById(idParty);
		gc.createGameParty(party);
		System.out.println("Games was created in party "+idParty);
		pc.initCurrentGame(idParty);
		
		// Ajout de l'utilisateur createur dans la partie
		//pc.addUser(idParty,idUser);
		// Ajout de l'utilisateur createur dans le game courant
		//gc.addUserGame(idParty, idUser);
		
		getServletContext().getRequestDispatcher("/joinParty.jsp").forward(request,response);
	}

}
