package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerDB.PartyManagerLocal;
import managerDB.UserManagerLocal;
import model.User;

/**
 * Servlet implementation class PartyCreation
 */
public class PartyCreation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	@EJB
	PartyManagerLocal pm;
	@EJB
	UserManagerLocal um;
	
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

		String idUser = session.getAttribute("idUser").toString();
		
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
		User user = um.getUserById(Integer.parseInt(idUser));
		if(pm.getIdByName(nameParty) != -1){
			String message = "name already exist !";
			getServletContext().getRequestDispatcher("/partyCreation.jsp?message="+message).forward(request,response);
			return;
		}
		pm.createPartie(nameParty,descriptionParty,user);
		int idParty = pm.getIdByName(nameParty);
		
		//request.set
		//getServletContext().getRequestDispatcher("/LinkBowser").forward(request,response);
		out.print("Coucou");
	}

}
