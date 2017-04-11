package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

		String login = request.getParameter("login");
	    String password = request.getParameter("password");  
	    
	    User user = null;
		try {
			user = DBUtils.findUser(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    if(user != null) {
	    	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    	request.getSession().setAttribute("user", user);
	    	rd.include(request,response); 
	    } else {
	    	RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
	    	request.setAttribute("success", false);
	    	request.setAttribute("login", login);
	    	rd.include(request,response);
	    }
	}
}