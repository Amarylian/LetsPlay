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
 * Klasa implementująca Servlet do obsługi przypominania hasła
 * obsługuje stronę przypominania hasła
 */
public class PasswordReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordReminderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Obsługa formularza przypomnienia hasla
	 * Pobiera email z formularza i przesyła go do klasy łączącej z bazą danych, następnie wyświetla hasło użytkownika
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html; charset=UTF-8");
		String email = request.getParameter("email");
    	RequestDispatcher rd=request.getRequestDispatcher("passwordReminder.jsp");  
	    User user = null;
		try {
			user = DBUtils.findUser(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    if(user != null) {
	    	request.setAttribute("password", user.getPassword());
	    	request.setAttribute("success", true);
	    } else {
	    	request.setAttribute("password", null);
	    	request.setAttribute("success", false);
	    }
    	rd.include(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
