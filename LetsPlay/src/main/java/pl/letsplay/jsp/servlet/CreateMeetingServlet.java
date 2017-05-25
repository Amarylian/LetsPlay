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
 * Klasa implementująca Servlet do tworzenia spotkania
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
	 * Obsługa formularza.
	 * Pobiera dane z formularza tworzenia spotkania i przesyła je do klasy łączącej z bazą danych, następnie pokazuje ewentualny komunikat o błędzie
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String priv=request.getParameter("priv");
		boolean pv;
		if(priv.equals("prywatne"))pv=true;
		else pv=false;
		String city=request.getParameter("city");
		String date=request.getParameter("dat");
		String time=request.getParameter("tim");
		String address=request.getParameter("address");
		String address2=request.getParameter("address2");
		boolean ad2;
		if(address2!=null)ad2=true;
		else ad2=false;
		String number=request.getParameter("number");
		int num= new Integer(number);
		String attentions=request.getParameter("attentions");
		Meeting res=null;
		try {
			int id = ((User) request.getSession().getAttribute("user")).getUser_id();
			res = DBUtils.createMeeting(id,pv, city, date, time, address, ad2, num, attentions);
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
