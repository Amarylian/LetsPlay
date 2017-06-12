package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Meeting;
import pl.letsplay.utils.DBUtils;

/**
 * Klasa implementująca Servlet do wyświetlania wszystkich zakończonych spotkań
 */
public class GetAllEndMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllEndMeetingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Obsługa przekierowania na podsumowanie spotkania
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
		String button = request.getParameter("button");
		Meeting m = null;
		try {
			m = DBUtils.findMeeting(Integer.parseInt(button));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(m != null) {
			RequestDispatcher rd=request.getRequestDispatcher("summary.jsp");
	    	request.getSession().setAttribute("meeting", m);
	    	rd.include(request,response); 
	    } else {
	    	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    	rd.include(request,response);
	    }
	}

}
