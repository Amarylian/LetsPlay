package pl.letsplay.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;
import pl.letsplay.utils.MyUtils;

/**
 * In case, the user logined and  remembered information in previous access (for example the day before). 
 * And now the user return, this Filter will check the Cookie information stored by the browser and automatic Login. 
 * @author LU
 *
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
	public CookieFilter() {
	   }
	 
	   public void init(FilterConfig fConfig) throws ServletException {
	 
	   }
	 
	   public void destroy() {
	 
	   }
	 
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	       HttpServletRequest req = (HttpServletRequest) request;
	       HttpSession session = req.getSession();
	 
	       User userInSession = MyUtils.getLoginedUser(session);
	    
	       if (userInSession != null) {
	           session.setAttribute("COOKIE_CHECKED", "CHECKED");
	           chain.doFilter(request, response);
	           return;
	       }
	 
	    
	       // Connection was created in JDBCFilter.
	       Connection conn = MyUtils.getStoredConnection(request);
	 
	  
	       // Flag check cookie
	       String checked = (String) session.getAttribute("COOKIE_CHECKED");
	       if (checked == null && conn != null) {
	           String userName = MyUtils.getUserEmailInCookie(req);
	           try {
	               User user = DBUtils.findUser(conn, userName);
	               MyUtils.storeLoginedUser(session, user);
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	    
	           // Mark checked.
	           session.setAttribute("COOKIE_CHECKED", "CHECKED");
	       }
	 
	       chain.doFilter(request, response);
	   }

}
