package linkParty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.PartyController2Local;

/**
 * Servlet implementation class ScoreParty
 */
public class ScoreParty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	PartyController2Local pc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreParty() {
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
		
		int idParty = Integer.parseInt(request.getParameter("idParty"));
		
		TreeMap<String, Integer> listscore = pc.getAllScore(idParty);
		
		String recupScore;
		int i =1;
		recupScore = "<table><tr><td>Rank</td><td>Pseudo</td><td>Score</td> ";
		for(String login : listscore.keySet()){
			recupScore = recupScore + "<tr>" +
								"<td>" + i + ". </td>" +
								"<td>" + login +" : </td>" +
								"<td>" + listscore.get(login) + "</td><tr>";
			i++;
		}
		
		recupScore = recupScore + "</table>";
		out.print(recupScore);
		out.close();
	}

}
