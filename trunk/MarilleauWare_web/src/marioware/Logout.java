package marioware;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllerEJB.UserControllerLocal;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserControllerLocal uc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
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
		
		uc.deleteUser(idUser);
	
		session.invalidate();
		String message = "Logout Ok";
		getServletContext().getRequestDispatcher("/index.jsp?message="+message).forward(request,response);
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
