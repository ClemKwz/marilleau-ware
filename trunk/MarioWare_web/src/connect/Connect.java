package connect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marioware_ejb.*;

import java.lang.Math;




/**
 * Servlet implementation class Connect
 */
@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int GETRESPONSE = 2;
	private static final int INIT = 1;
	
	@EJB
	PartiesManagerLocal partiesManager;
    private PrintWriter out;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		
		int x,y;
		x = 200;
		y = 300;
		
		
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
		int x1,y1;
		double d;
		x1 = 10;
		y1 = 10;
		
		if(action==GETRESPONSE){
			int x2 = Integer.parseInt(request.getParameter("x"));
			int y2 = Integer.parseInt(request.getParameter("y"));
			d = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
			out.print("Distance entre les points : " + (int)d + " pixels");
			System.out.println("Distance entre les points : " + d + " pixels");
			int doubl = (int) d;
			partiesManager.setTest(doubl);
			
		}else{
			out.print(x1 + ";" + y1);
		}
		out.close();
		
	}

}