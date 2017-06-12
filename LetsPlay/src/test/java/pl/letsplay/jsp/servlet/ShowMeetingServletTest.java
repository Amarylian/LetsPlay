package pl.letsplay.jsp.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBUtils.class)
public class ShowMeetingServletTest {
	
	private HttpServletRequest request;       
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;

	@Before
	public void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class);
        rd = Mockito.mock(RequestDispatcher.class);
        session = Mockito.mock(HttpSession.class);
        PowerMockito.mockStatic(DBUtils.class);
	}

	@Test
	public void testDoPostSuccess() throws ServletException, IOException, SQLException {
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("meeting")).thenReturn(new Meeting());
        Mockito.when(session.getAttribute("user")).thenReturn(new User());
		
		
		
		new ShowMeetingServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.joinMeeting(Mockito.any(Meeting.class),Mockito.any(User.class));
		
		(Mockito.verify(request, Mockito.times(2))).getSession();
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("user"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("meeting"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}
	
	@Test
	public void testDoPostFail() throws ServletException, IOException, SQLException {
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("meeting")).thenReturn(null);
        Mockito.when(session.getAttribute("user")).thenReturn(new User());
		
		
		
		new ShowMeetingServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(0));
		DBUtils.joinMeeting(Mockito.any(Meeting.class),Mockito.any(User.class));
		
		
		(Mockito.verify(request, Mockito.times(2))).getSession();
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("user"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("meeting"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}

}
