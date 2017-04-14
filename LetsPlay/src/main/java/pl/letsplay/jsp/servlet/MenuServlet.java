package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
    	rd.include(request,response); 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
		String button = request.getParameter("button");
		if(button.equals("Register")) {
			RequestDispatcher rd=request.getRequestDispatcher("/register.jsp");  
	    	rd.include(request,response); 
		} else if(button.equals("CreateMeeting")) {
			RequestDispatcher rd=request.getRequestDispatcher("/createMeeting.jsp");  
	    	rd.include(request,response); 
		} else if(button.equals("Login")) {
			RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");  
	    	rd.include(request,response); 
		} else if(button.equals("Logout")) {
			request.getSession().setAttribute("user", null);
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
	    	rd.include(request,response); 
		} else {
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
	    	rd.include(request,response); 
		}
	}
}
