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

import model.ChatParty;

import controllerEJB.PartyController2Local;

/**
 * Servlet implementation class Chat
 */
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PartyController2Local partyController;
	
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int idParty = Integer.parseInt(request.getParameter("idParty"));	
		int idUser = Integer.parseInt( session.getAttribute("idUser").toString());
		
		int action = Integer.parseInt(request.getParameter("action"));
		
		if(action==1){
			//GETALLMESSAGE
			out.print("Chat<br>");
			List<ChatParty> lcp = partyController.getAllMessages(idParty);
			if(lcp==null){
				out.print("no message<br>");
			}else{
				for(int i=0;i<lcp.size();i++){
					out.print(lcp.get(i).getIdUser()+" dit : "+lcp.get(i).getMessage()+" <br> ");
				}
			}
			//out.print("<script  type=\"javascript\">initChat("+idParty+");</script>");
			
			out.print("End Chat<br>");
		}else if(action==2){
			//SEND MESSAGE
			String msg = request.getParameter("message");
			System.out.println("idParty = "+idParty+", idUser = "+idUser+", msg = "+msg);
			partyController.addMessage(idParty, idUser, msg);
		}
	}

}
