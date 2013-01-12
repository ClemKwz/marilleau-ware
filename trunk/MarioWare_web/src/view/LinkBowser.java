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

import controllerEJB.*;

/**
 * Servlet implementation class AddTamere
 */
public class LinkBowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BowserControllerLocal bowser;
	
	@EJB
	PartyManagerLocal pm;
	@EJB
	UserManagerLocal um;

	private static final int INIT = 1;
	private static final int GETRESPONSE = 2;
	private static final int GETENDGAME = 3;
	
	private static final int CREATEPARTY = 10;
	private static final int VIEWPARTIES = 11;
	
	private int x = 0;
	private int i = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkBowser() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//list.addOneTest(Integer.getInteger(request.getParameter("val")));
		//bowser.addParty(1, "PartyController");

		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"+
				"<html>"+
					"<head>"+
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
						"<link rel=\"stylesheet\" href=\"css/main.css\" type=\"text/css\" media=\"screen\" charset=\"utf-8\" />"+
						"<title>Marilleau ware</title>"+
					"</head>"+
				"<body>");

		out.print("<a href=\"LinkBowser?req=1\">creeeeer une partie</a></br>");
		out.print("<a href=\"LinkBowser?req=2\">recuperer valeur</a></br>");

		out.print("<form action=\"\" method=\"POST\">" +
				"<input type=\"text\" name=\"valeurgame\"></input>" +
				"<input type=\"submit\"></input>" +
				"</form>");
		
		out.print("</body>" + "</header>" + "</html>");
		
		// Recuperation du champ req
		String req = request.getParameter("req");
		
		// Verification de la presence du req
		if (!(req == null || req.equals(""))) {
			out.print("req ok = " + req);
			if(req.equals("1")){
				bowser.addParty("PartyController",1);
				
				String s = bowser.displayHTML();
				out.print("</br>" +s);
			}
			if(req.equals("2")){
				out.print("</br>get result : " + bowser.getValeurGame(0, 1));
				
				String s = bowser.displayHTML();
				out.print("</br>" +s);
			}
		}
		
		//int i = list.showTest(1);
		//String s = "HAHA"+i;
		//out.print(s);
		
		out.close();
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
		
		int action = Integer.parseInt(request.getParameter("action"));
		int x1,y1;
		double d;
		x1 = 10;
		y1 = 10;
		int idUser = 25;
		int idParty = 0;
		int idGame = 0;
		
		switch(action){
			case CREATEPARTY:
				/*
				String idPartyCreate = request.getParameter("idParty");
				bowser.addParty("PartyController",Integer.parseInt(idPartyCreate));
				String s = bowser.displayHTML();
				out.print("</br>" +s);
				*/

				String idUserG = session.getAttribute("idUser").toString();
				
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
				User user = um.getUserById(Integer.parseInt(idUserG));
				if(pm.getIdByName(nameParty) != -1){
					String message = "name already exist !";
					getServletContext().getRequestDispatcher("/partyCreation.jsp?message="+message).forward(request,response);
					return;
				}
				pm.createPartie(nameParty,descriptionParty,user);
				int idPartyCreate = pm.getIdByName(nameParty);
				
				bowser.addParty("PartyController",idPartyCreate);
				out.print("Party created<br>");
				out.print("</br>" +bowser.displayHTML());
				
				break;
			case VIEWPARTIES:
				out.print("</br>" +bowser.displayHTML());
				break;
			case INIT:
				
				break;
			case GETRESPONSE:
				bowser.addParty("PartyController",1);
				i = 0;
				int x2 = Integer.parseInt(request.getParameter("x"));
				int y2 = Integer.parseInt(request.getParameter("y"));
				d = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
				//out.print("Distance entre les points : " + (int)d + " pixels");
				//System.out.println("Distance entre les points : " + d + " pixels");
				
				bowser.setValeurGame(0, 0,(int)d);
				out.print("Distance entre les points : " + (int)d + " pixels");
				out.print("Bowser dit : <br> " + bowser.displayHTML());
				//gameManager.play(request.getSession().getAttribute("sessionID"), doubl);
				break;
			case GETENDGAME:/* Fonction apellee chaque demi seconde par chaque joueur ayant termine pour avoir les scores*/
				i++;
				//TODO: On renvoie "Gagne ou perdu" + nb POints?
				if(i<10){
					out.print("Wait");
				}else{
					out.print("Gagne");
					out.print("Bowser dit : <br> " + bowser.displayHTML());
				}
				break;
			default:
				//gameManager.connect();
				out.print(idUser + ";" + idParty + ";" + idGame + ";" + x1 + ";" + y1);
				break;
		}
		
		x++;
		System.out.println("x => " + x);
		
		out.close();
		
		/*
		
		// TESTS nuit de la PAD I
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"+
				"<html>"+
					"<head>"+
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
						"<link rel=\"stylesheet\" href=\"css/main.css\" type=\"text/css\" media=\"screen\" charset=\"utf-8\" />"+
						"<title>Marilleau ware</title>"+
					"</head>"+
				"<body>");
		
		out.print("<a href=\"LinkBowser?req=1\">creer une partie</a></br>");
		out.print("<a href=\"LinkBowser?req=2\">recuperer valeur</a></br>");

		out.print("<form action=\"\" method=\"POST\">" +
				"<input type=\"text\" name=\"valeurgame\"></input>" +
				"<input type=\"submit\"></input>" +
				"</form>");
		
		out.print("</body>" + "</header>" + "</html>");
		
		// Recuperation du champ pseudo
		String req = request.getParameter("valeurgame");
		
		// Verification de la presence du pseudo
		if (!(req == null || req.equals(""))) {
			out.print("valeurgame ok = " + req);
			bowser.setValeurGame(0, 1,Integer.valueOf(req));
			String s = bowser.affiche();
			out.print("</br>" +s);
		}
		*/
	}
}
