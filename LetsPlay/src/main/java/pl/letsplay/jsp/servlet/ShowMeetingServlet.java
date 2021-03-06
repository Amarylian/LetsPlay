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
 * Klasa implementująca Servlet do wyświetlania szczegółów spotkania
 * obsługuje stronę szczegółów spotkania
 */
public class ShowMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMeetingServlet() {
        super();
    }

	/**
	 * Obsługa przycisku szczegółów spotkania
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		Meeting meeting = (Meeting) request.getSession().getAttribute("meeting");
		User user = (User) request.getSession().getAttribute("user");
		if (meeting == null || user == null) {
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    	rd.include(request,response); 
		} else {
			try {
				DBUtils.joinMeeting(meeting, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd=request.getRequestDispatcher("showMeeting.jsp");  
	    	rd.include(request,response); 
		}

		
	}

}
