package linkGame;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		int idPlayer = (int) request.getSession().getAttribute("idUser");
		System.out.println("idUser => " + idPlayer);
		int idParty = pc.getIdPartyByIdUser(idPlayer);
		int idGame = pc.getIdGameByIdParty(idParty);
		
		System.out.println("Donnée recu de l'user : " + idPlayer);
		
		switch(action){
		case INIT : 
			//gameManager.connect();
			out.print(idPlayer + ";" + idParty + ";" + idGame + ";" + x1 + ";" + y1);
			break;
		case GETRESPONSE :
			int x2 = Integer.parseInt(request.getParameter("x"));
			int y2 = Integer.parseInt(request.getParameter("y"));
			d = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
			out.print("Distance entre les points : " + (int)d + " pixels");
			System.out.println("distance : "+ d);
			//System.out.println("Distance entre les points : " + d + " pixels");
			int doubl = (int) d;
			
			
			
			//gameManager.play(request.getSession().getAttribute("sessionID"), doubl);
			gc.addScore(idGame, idPlayer, doubl);
			
			break;
		default :
			break;
		}
		x++;
		//System.out.println("x => " + x);
		out.close();
	}

}
