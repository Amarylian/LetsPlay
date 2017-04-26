package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.letsplay.beans.Meeting;
import pl.letsplay.utils.DBUtils;

/**
 * Servlet implementation class FindMeetingServlet
 */
public class FindMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        
		Map<String, String> attributes = new HashMap<String, String>();
		
		String city = request.getParameter("city");
		String dateFrom = request.getParameter("date_from");
		String dateTo = request.getParameter("date_to");
		//String time = request.getParameter("time");
		String address = request.getParameter("address");
		String attentions = request.getParameter("attentions");
		
		if(city != null && city != "") {
			attributes.put("city", city);
		}
		if(dateFrom != null && dateFrom != "") {
			DateFormat format = new SimpleDateFormat("Y-L-d", Locale.ENGLISH);
			try {
				format.parse(dateFrom);
				attributes.put("date_from", dateFrom);
			} catch (ParseException e) {
				
			}
		}
		if(dateTo != null && dateTo != "") {
			DateFormat format = new SimpleDateFormat("Y-L-d", Locale.ENGLISH);
			try {
				format.parse(dateTo);
				attributes.put("date_to", dateTo);
			} catch (ParseException e) {
				
			}
		}
		/*if(time != null && time != "") {
			DateFormat format = new SimpleDateFormat("H:m", Locale.ENGLISH);
			try {
				Date date = format.parse(time);
				attributes.put("time", time);
			} catch (ParseException e) {
				
			}
		}*/
		if(address != null && address != "") {
			attributes.put("address", address);
		}
		if(attentions != null && attentions != "") {
			attributes.put("attentions", attentions);
		}
		ArrayList<Meeting> listOfMeetings = null;
		//try {
			listOfMeetings = (ArrayList<Meeting>) DBUtils.queryMeeting(attributes);
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//}
		if (listOfMeetings != null) {
			request.getSession().setAttribute("meetings", listOfMeetings);
			moveToSide(request, response, "/getAllMettings.jsp");
		} else {
			moveToSide(request, response, "/index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}

}
