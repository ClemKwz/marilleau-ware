package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllerEJB.*;


/**
 * Servlet implementation class AddTamere
 */
public class LinkBowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BowserControllerLocal bowser;
	
	private static final int GETENDGAME = 3;
	private static final int GETRESPONSE = 2;
	private static final int INIT = 1;
	private int x =0;
	
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
	//	list.addOneTest(Integer.getInteger(request.getParameter("val")));
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

		out.print("<a href=\"LinkBowser?req=1\">creer une partie</a></br>");
		out.print("<a href=\"LinkBowser?req=2\">recuperer valeur</a></br>");

		out.print("<form action=\"\" method=\"POST\">" +
				"<input type=\"text\" name=\"valeurgame\"></input>" +
				"<input type=\"submit\"></input>" +
				"</form>");
		
		out.print("</body>" +
			"</header>" +
		"</html>");
		
		// Recuperation du champ pseudo
		String req = request.getParameter("req");
		
		// Verification de la presence du pseudo
		if (!(req == null || req.equals(""))) {
			out.print("req ok = " + req);
			if(req.equals("1")){
				bowser.addParty(1, "PartyController");
				
				String s = bowser.affiche();
				out.print("</br>" +s);
			}
			if(req.equals("2")){
				out.print("</br>get result : " + bowser.getValeurGame(0, 1));
				
				String s = bowser.affiche();
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

		
		bowser.addParty(1, "PartyController");

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		int action = Integer.parseInt(request.getParameter("action"));
		int x1,y1;
		double d;
		x1 = 10;
		y1 = 10;
		
		int idUser = 25;
		int idParty = 0;
		int idGame = 0;
		
		if(action==GETRESPONSE){
			int x2 = Integer.parseInt(request.getParameter("x"));
			int y2 = Integer.parseInt(request.getParameter("y"));
			d = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
			//out.print("Distance entre les points : " + (int)d + " pixels");
			//System.out.println("Distance entre les points : " + d + " pixels");
			int doubl = (int) d;
			bowser.setValeurGame(0, 1,(int)d);
			out.print("Distance entre les points : " + (int)d + " pixels");
			out.print("Bowser dit : <br> " + bowser.affiche());
			
			//gameManager.play(request.getSession().getAttribute("sessionID"), doubl);
			
		}else{
			if(action==GETENDGAME){
				//TODO: On renvoie "GagnÃ© ou perdu" + nb POints?
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("Gagné");
			}
			//gameManager.connect();
			out.print(idUser + ";" + idParty + ";" + idGame + ";" + x1 + ";" + y1);
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
		
		out.print("</body>" +
			"</header>" +
		"</html>");
		
		// Recuperation du champ pseudo
		String req = request.getParameter("valeurgame");
		
		// Verification de la presence du pseudo
		if (!(req == null || req.equals(""))) {
			out.print("valeurgame ok = " + req);
			bowser.setValeurGame(0, 1,Integer.valueOf(req));
			
			String s = bowser.affiche();
			out.print("</br>" +s);
		}*/
	}
}
