package pl.letsplay.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
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
		User u = (User) request.getSession().getAttribute("user");
		try{
			if(ifMeeting.equals("nie"))
			{
				DBUtils.givePoints(u.getUser_id(), 0);
				DBUtils.givePoints(m.getUserId(), -1);
			}
			else if(ifYou.equals("nie"))
			{
				DBUtils.givePoints(u.getUser_id(), -1);
				DBUtils.givePoints(m.getUserId(), 1);
			}
			else
			{
				DBUtils.givePoints(u.getUser_id(), 1);
				DBUtils.givePoints(m.getUserId(), 1);
			}
			DBUtils.scoreMeeting(u.getUser_id(), m.getId());
		}catch(Exception ex){
			//TODO
		}
		moveToSide(request, response, "/index.jsp");
	}

	private void moveToSide(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(url);  
    	rd.include(request,response); 
	}
}


