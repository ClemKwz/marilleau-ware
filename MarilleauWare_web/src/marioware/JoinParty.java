package marioware;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Party;

import controllerEJB.GameController2Local;
import controllerEJB.PartyController2Local;

/**
 * Servlet implementation class JoinParty
 */
public class JoinParty extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	PartyController2Local partyController;
	@EJB
	GameController2Local gameController;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinParty() {
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
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();

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
		int action = Integer.parseInt(request.getParameter("action"));
		
		if(action==1){
			// Visualisation des parties
			List<Party> lp = partyController.getListOfParty();
			
			out.print("Cliquez sur une partie pour la rejoindre<br>");
			for (Party resultElement : lp) {
				out.print("<a href=\"#\" onclick=\"joinPartie('"+resultElement.getIdParty()+"','"+idUser+"');\">name : "+resultElement.getName()+", desc : "+resultElement.getDescription()+"</a><br>");
			}
		}
		if(action==2){
			// Utilisateur joins une partie
			int idParty = Integer.parseInt(request.getParameter("idParty"));
			
			// Ajout de l'utilisateur dans la partie
			partyController.addUser(idParty,idUser);
			System.out.println("User "+idUser+" added to party "+idParty);
			
			// Ajout de l'utilisateur dans le game courant
			gameController.addUserGame(idParty, idUser);
			
			getServletContext().getRequestDispatcher("/party.jsp?idParty="+idParty).forward(request,response);
		}
		
		out.close();
	}
}
