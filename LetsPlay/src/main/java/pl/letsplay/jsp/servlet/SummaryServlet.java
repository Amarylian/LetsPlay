package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Meeting;
import pl.letsplay.utils.DBUtils;

/**
 * Klasa implementująca Servlet do podsumowania spotkania
 * obsługuje stronę podsumowania spotkania
 */
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Obsługa formularza.
	 * Pobiera dane z formularza podsumowania spotkania i przesyła je do klasy łączącej z bazą danych
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String ifMeeting = request.getParameter("ifmeeting");
		String ifYou = request.getParameter("ifyou");
		Meeting m = (Meeting) request.getSession().getAttribute("meeting");
		if(ifMeeting.equals("nie"))
		{
			//db.givePoints(int pointsForUser, int pointsForAdmin, Meeting m)
			//DBUtils.givePoints(0,-1, m);
		}
		else if(ifYou.equals("nie"))
		{
			//DBUtils.givePoints(-1,1, m);
		}
		else
		{
			//DBUtils.givePoints(1, 1, m);
		}
		moveToSide(request, response, "/index.jsp");
	}

	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}
}


