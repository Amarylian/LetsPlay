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
 * Servlet implementation class CreateMeetingServlet. 
 * obsługuje stronę tworzenia spotkania
 */
public class CreateMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * not used
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Obsługa formularza.
	 * Pobiera dane z formularza tworzenia spotkania i przesyła je do klasy łączącej z bazą danych, następnie pokazuje ewentualny komunikat o błędzie
	 * @see DBUtils#createMeeting(java.sql.Connection, String, String, String, String, String, String, String, String)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String priv=request.getParameter("priv");
		String city=request.getParameter("city");
		String date=request.getParameter("dat");
		String time=request.getParameter("tim");
		String address=request.getParameter("address");
		String address2=request.getParameter("address2");
		String number=request.getParameter("number");
		String attentions=request.getParameter("attentions");
		Meeting res=null;
		try {
			res = DBUtils.createMeeting(true, city, date, time, address, true, 5, attentions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("success", false);
			RequestDispatcher rd=request.getRequestDispatcher("createMeeting.jsp");
			rd.include(request,response);
		}
		if(res!=null){
			request.setAttribute("success", true);
			RequestDispatcher rd=request.getRequestDispatcher("createMeeting.jsp");
			rd.include(request,response);
		} else {
			request.setAttribute("success", false);
			RequestDispatcher rd=request.getRequestDispatcher("createMeeting.jsp");
			rd.include(request,response);
		}

	}

}
