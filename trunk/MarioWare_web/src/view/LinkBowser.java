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
				out.print("</br>get result : " + bowser.getValeurGame(0, 0));
				
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
			bowser.setValeurGame(0, 0,Integer.valueOf(req));
			
			String s = bowser.affiche();
			out.print("</br>" +s);
		}
	}
}
