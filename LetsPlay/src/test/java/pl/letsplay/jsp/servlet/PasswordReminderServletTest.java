package pl.letsplay.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBUtils.class)
public class PasswordReminderServletTest {
	private HttpServletRequest request;       
	private HttpServletResponse response;
	private RequestDispatcher rd;
	
	@Before
	public void setUp(){
		request = Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class);
        rd = Mockito.mock(RequestDispatcher.class);
        PowerMockito.mockStatic(DBUtils.class);
	}

	@Test
	public void testDoGetSuccess() throws SQLException, ServletException, IOException {
        
        Mockito.when(request.getParameter(Mockito.eq("email"))).thenReturn("adres");
        Mockito.when(request.getRequestDispatcher(Mockito.eq("passwordReminder.jsp"))).thenReturn(rd);
		
		PowerMockito.when(DBUtils.findUser(Mockito.eq("adres"))).thenReturn(new User());
		
		new PasswordReminderServlet().doGet(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findUser(Mockito.eq("adres"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("email"));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("password"), Mockito.any(String.class));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("success"), Mockito.eq(true));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}

	@Test
	public void testDoGetFail() throws SQLException, ServletException, IOException {
        
        Mockito.when(request.getParameter(Mockito.eq("email"))).thenReturn("adres");
        Mockito.when(request.getRequestDispatcher(Mockito.eq("passwordReminder.jsp"))).thenReturn(rd);
		
		PowerMockito.when(DBUtils.findUser(Mockito.eq("adres"))).thenReturn(null);
		
		new PasswordReminderServlet().doGet(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findUser(Mockito.eq("adres"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("email"));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("password"), Mockito.eq(null));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("success"), Mockito.eq(false));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}

}
