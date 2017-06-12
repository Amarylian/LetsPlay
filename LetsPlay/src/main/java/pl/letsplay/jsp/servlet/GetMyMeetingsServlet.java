package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Meeting;

/**
 * Klasa implementująca Servlet do obsługi wyświetlania  spotkań zalogowanego użytkownika
 * obsługuje stronę wyświetlania spotkań
 */
public class GetMyMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyMeetingsServlet() {
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
		doGet(request, response);
	}

	/**
	 * Meeting meeting = (Meeting)request.getSession().getAttribute("meeting");
	 * funkcja przekierwouje do tworzenia kolejnej edycji spotkania
	 */
	protected void createMeeting(Meeting meeting, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Meeting createMeeting = new Meeting(-1, false, meeting.getCity(), null, null, meeting.getAddress(), false, 0, meeting.getMaxNumber(), meeting.getAttentions());
		request.getSession().setAttribute("CreateMeeting", createMeeting);
		this.moveToSide(request, response, "createMeeting.jsp");
	}
	/**
	 * funkcja pomocnicza do przekierowania na inną stronę
	 */
	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}
}
