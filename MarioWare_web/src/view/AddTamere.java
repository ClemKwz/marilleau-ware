package view;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marioware_ejb.ListLocal;

/**
 * Servlet implementation class AddTamere
 */
public class AddTamere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	ListLocal list;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTamere() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	list.addOneTest(Integer.getInteger(request.getParameter("val")));
		list.addOneTest(1);
		list.addOneTest(2);
		list.addOneTest(3);
		list.addOneTest(4);
		list.addOneTest(5);
		
		String s = list.print();
		//int i = list.showTest(1);
		//String s = "HAHA"+i;
		response.getWriter().print(s);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
