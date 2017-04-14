package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.User;

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
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
		String button = request.getParameter("button");
		User user = (User) request.getSession().getAttribute("user");
		if(button == null) {
			button = "";
		}
		
		if(button.equals("Register")) {
			if(user != null) {
				moveToSide(request, response, "/index.jsp");
			} else {
				moveToSide(request, response, "/register.jsp");
			}
		} else if(button.equals("CreateMeeting")) {
			if(user == null) {
				moveToSide(request, response, "/index.jsp");
			} else {
				moveToSide(request, response, "/createMeeting.jsp");
			}
		} else if(button.equals("Login")) {
			if(user != null) {
				moveToSide(request, response, "/index.jsp");
			} else {
				moveToSide(request, response, "/login.jsp");
			}
		} else if(button.equals("Logout")) {
			request.getSession().setAttribute("user", null);
			moveToSide(request, response, "/index.jsp");
		} else {
			moveToSide(request, response, "/index.jsp");
		}
	}
	
	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}
}
