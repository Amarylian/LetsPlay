package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

/**
 * Servlet implementation class RegisterServlet. 
 * obsługuje stronę rejestrowania użytkownika
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Obsługa formularza do rejestracji.
	 * Pobiera dane z formularza rejestracji i przesyła je do klasy łączącej z bazą danych, następnie pokazuje ewentualny komunikat o błędzie.
	 * @see DBUtils#createMeeting(java.sql.Connection, String, String, String, String, String, String, String, String)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String login = request.getParameter("login");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		String passwd2 = request.getParameter("passwd2");
		if(!passwd.equals(passwd2))
		{
			request.setAttribute("password", false);
			RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
			rd.include(request,response);
		}
		else
		{
			User user = null;

			try {
				 user = DBUtils.createUser(login,name, surname, email, passwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user!=null)
			{
				request.setAttribute("success", true);
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.include(request,response);
			}
			else
			{
				request.setAttribute("success", false);
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
				rd.include(request,response);
			}
		}
		
		

			

	}

}
