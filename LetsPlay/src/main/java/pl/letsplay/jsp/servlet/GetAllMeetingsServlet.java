package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

/**
 * Servlet implementation class GetAllMeetingsServlet
 */
public class GetAllMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllMeetingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html; charset=UTF-8");
		String button = request.getParameter("button");
		Meeting m = null;
		try {
			m = DBUtils.findMeeting(button);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(m != null) {
	    	RequestDispatcher rd=request.getRequestDispatcher("getAllMeetings.jsp");  
	    	request.getSession().setAttribute("meeting", m);
	    	rd.include(request,response); 
	    } else {
	    	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    	rd.include(request,response);
	    }
	}

}
