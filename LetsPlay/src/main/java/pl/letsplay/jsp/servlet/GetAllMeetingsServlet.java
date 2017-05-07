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
 * Klasa implementująca Servlet do obsługi wyświetlania wszystkich spotkań
 * obsługuje stronę wyświetlania spotkań
 */
public class GetAllMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllMeetingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Obsługa wybierania widoku szczegółowego. Po kliknięciu na przycisk, konkretne spotkanie zostaje zapisane jako atrybut sesji.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html; charset=UTF-8");
		String button = request.getParameter("button");
		Meeting m = null;

			try {
				m = DBUtils.findMeeting(Integer.parseInt(button));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		if(m != null) {
	    	//RequestDispatcher rd=request.getRequestDispatcher("getAllMeetings.jsp");  
			RequestDispatcher rd=request.getRequestDispatcher("showMeeting.jsp");
	    	request.getSession().setAttribute("meeting", m);
	    	rd.include(request,response); 
	    } else {
	    	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    	rd.include(request,response);
	    }
	}

}
