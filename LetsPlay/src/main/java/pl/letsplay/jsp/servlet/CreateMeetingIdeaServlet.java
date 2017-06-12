package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.Idea;
import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

/**
 * Klasa implementująca Servlet do tworzenia pomysłu spotkania
 * obsługuje stronę tworzenia pomysłu spotkania
 */
public class CreateMeetingIdeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateMeetingIdeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Obsługa formularza.
	 * Pobiera dane z formularza tworzenia pomysłu spotkania i przesyła je do klasy łączącej z bazą danych
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String city=request.getParameter("city");
		String attentions=request.getParameter("attentions");
		Idea res=null;
		try {
			int id = ((User) request.getSession().getAttribute("user")).getUser_id();
			res = DBUtils.createMeetingIdea(id, city, attentions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd=request.getRequestDispatcher("createMeetingIdea.jsp");
			rd.include(request,response);
		}
		if(res!=null){
			RequestDispatcher rd=request.getRequestDispatcher("Index.jsp");
			rd.include(request,response);
		} else {
			RequestDispatcher rd=request.getRequestDispatcher("createMeetingIdea.jsp");
			rd.include(request,response);
		}

	}
	

}
