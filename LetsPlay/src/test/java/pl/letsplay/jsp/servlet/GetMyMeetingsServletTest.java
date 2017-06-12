package pl.letsplay.jsp.servlet;

import static org.junit.Assert.*;

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
import pl.letsplay.utils.DBUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBUtils.class)
public class GetMyMeetingsServletTest {

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
	public void testDoPostSuccess() throws SQLException, ServletException, IOException {
		Mockito.when(request.getParameter(Mockito.eq("button"))).thenReturn("2");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
		
		PowerMockito.when(DBUtils.findMeeting(Mockito.eq(2))).thenReturn(new Meeting());
		
		
		new GetMyMeetingsServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findMeeting(Mockito.eq(2));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("button"));
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("createMeeting.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
		(Mockito.verify(session, Mockito.times(1))).setAttribute(Mockito.eq("CreateMeeting"), Mockito.any(Meeting.class));
	}
	
	@Test
	public void testDoPostFail()  throws SQLException, ServletException, IOException {
		Mockito.when(request.getParameter(Mockito.eq("button"))).thenReturn("2");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
		
		PowerMockito.when(DBUtils.findMeeting(Mockito.eq(2))).thenReturn(null);
		
		
		new GetMyMeetingsServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.findMeeting(Mockito.eq(2));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("button"));
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("getMyMeetings.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
		(Mockito.verify(session, Mockito.times(0))).setAttribute(Mockito.eq("CreateMeeting"), Mockito.any(Meeting.class));
	}

}
