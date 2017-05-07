package pl.letsplay.jsp.servlet;


import java.io.IOException;
import java.sql.SQLException;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBUtils.class)
public class LoginServletTest {
	
	private HttpServletRequest request;       
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
	
	@Before
	public void setUp(){
		request = Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class);
        rd = Mockito.mock(RequestDispatcher.class);
        session = Mockito.mock(HttpSession.class);
        PowerMockito.mockStatic(DBUtils.class);
	}
	

	@Test
	public void testDoGetSuccess() throws SQLException, ServletException, IOException {
        
        Mockito.when(request.getParameter(Mockito.eq("login"))).thenReturn("login");
        Mockito.when(request.getParameter(Mockito.eq("password"))).thenReturn("haslo");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
		
		PowerMockito.when(DBUtils.findUser(Mockito.eq("login"),Mockito.eq("haslo"))).thenReturn(new User());
		
		
		new LoginServlet().doGet(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findUser(Mockito.eq("login"),Mockito.eq("haslo"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("login"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("password"));
		(Mockito.verify(session, Mockito.times(1))).setAttribute(Mockito.eq("user"), Mockito.any(User.class));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
		
	}
	
	@Test
	public void testDoGetFail() throws SQLException, ServletException, IOException {
        
        Mockito.when(request.getParameter(Mockito.eq("login"))).thenReturn("login");
        Mockito.when(request.getParameter(Mockito.eq("password"))).thenReturn("haslo");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
		
		PowerMockito.when(DBUtils.findUser(Mockito.eq("login"),Mockito.eq("haslo"))).thenReturn(null);
		
		new LoginServlet().doGet(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findUser(Mockito.eq("login"),Mockito.eq("haslo"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("login"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("password"));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("success"), Mockito.eq(false));
		(Mockito.verify(request, Mockito.times(1))).setAttribute(Mockito.eq("login"), Mockito.eq("login"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}

}
