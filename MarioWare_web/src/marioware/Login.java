package marioware;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerDB.*;


//import marioware_ejb.PartiesManagerLocal;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	//@EJB
	//PartiesManagerLocal partiesManager;
	@EJB
	UserManagerLocal um;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperation du champ pseudo
		String pseudo = request.getParameter("pseudo").trim();
		
		// Verification de la presence du pseudo
		if (pseudo == null || pseudo.equals("")) {
			String message = "Enter your pseudo !";
			getServletContext().getRequestDispatcher("/index.jsp?message="+message).forward(request,response);
			return;
		}
		
		// Insertion BDD
		// TODO A voir suppression utilisateur de la BDD
		
		// Verification si le pseudo existe deja dans la DB
		if (um.findByPseudo(pseudo) == true) {
			String message = "Pseudo already exist !";
			getServletContext().getRequestDispatcher("/index.jsp?message="+message).forward(request,response);
			return;
		}
		
		// Ajout du nouvel utilisateur
		um.createUser(pseudo, "", 0);
		
		// Recuperation de l id du nouvel utilisateur
		int idUser = um.getIdByPseudo(pseudo);
		
		// Creation de la session utilisateur
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1800); // 30 Minutes
		String sessionID = session.getId();
		session.setAttribute("sessionID", sessionID);
		session.setAttribute("idUser", idUser);
		session.setAttribute("pseudoUser", pseudo);
		
		// Redirection vers la page home
		getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);
	}
}
