package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;
import pl.letsplay.beans.Idea;
import pl.letsplay.beans.Meeting;

/**
 * Klasa implementująca Servlet do obsługi menu
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
	 * Obsługa przycisków w menu
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
		} else if(button.equals("GetAllMeetings")) {
			ArrayList<Meeting> listOfMeetings = null;
			try {
				listOfMeetings = (ArrayList<Meeting>) DBUtils.queryMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (listOfMeetings != null) {
				request.getSession().setAttribute("meetings", listOfMeetings);
				moveToSide(request, response, "/getAllMeetings.jsp");
			} else {
				moveToSide(request, response, "/index.jsp");
			}
		} else if(button.equals("FindMeeting")) {
			moveToSide(request, response, "/findMeeting.jsp");
		} else if(button.equals("getAllEndMeetings")) {
			ArrayList<Meeting> listOfMeetings = null;
			try {
				//pobiera meetingi w których użytkownik brał udział lub jest założycielem
				listOfMeetings = (ArrayList<Meeting>) DBUtils.queryMeeting(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (listOfMeetings != null) {
				request.getSession().setAttribute("meetings", listOfMeetings);
				moveToSide(request, response, "/getAllEndMeetings.jsp");
			} else {
				moveToSide(request, response, "/index.jsp");
			}
		} else if(button.equals("getMyMeetings")) {
			ArrayList<Meeting> listOfMeetings = null;
			try {
				//pobiera meetingi których jest założycielem
				listOfMeetings = (ArrayList<Meeting>) DBUtils.myMeeting(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (listOfMeetings != null) {
				request.getSession().setAttribute("meetings", listOfMeetings);
				moveToSide(request, response, "/getMyMeetings.jsp");
			} else {
				moveToSide(request, response, "/index.jsp");
			}
		} else if(button.equals("getMeetingIdea")) {
			ArrayList<Idea> listOfIdeas = null;
			try {
				listOfIdeas = (ArrayList<Idea>) DBUtils.queryIdeas();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (listOfIdeas != null) {
				request.getSession().setAttribute("ideas", listOfIdeas);
				moveToSide(request, response, "/getAllIdeas.jsp");
			} else {
				moveToSide(request, response, "/index.jsp");
			}
		} else if(button.equals("CreateIdea")) {
			moveToSide(request, response, "/createMeetingIdea.jsp");
		} else {
			moveToSide(request, response, "/index.jsp");
		}
	}
	
    /**
	 * Obsługa przekierowania do innej strony
	 */
	
	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}
}
