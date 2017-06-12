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

import pl.letsplay.beans.Idea;
import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
import pl.letsplay.utils.DBUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBUtils.class)
public class SummaryServletTest {

	private HttpServletRequest request;       
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
	private User user;
	private Meeting meeting;
	
	@Before
	public void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class);
        rd = Mockito.mock(RequestDispatcher.class);
        session = Mockito.mock(HttpSession.class);
        user = Mockito.mock(User.class);
        meeting = Mockito.mock(Meeting.class);
        PowerMockito.mockStatic(DBUtils.class);
	}

	@Test
	public void testDoPostSuccessCaseN() throws ServletException, IOException, SQLException {
		Mockito.when(request.getParameter("ifmeeting")).thenReturn("nie");
		Mockito.when(request.getParameter("ifyou")).thenReturn("tak");
		
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("meeting")).thenReturn(meeting);
        Mockito.when(user.getUser_id()).thenReturn(10);
        Mockito.when(meeting.getUserId()).thenReturn(15);
        Mockito.when(meeting.getId()).thenReturn(5);
		
		
		new SummaryServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(10),Mockito.eq(0));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(15),Mockito.eq(-1));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.scoreMeeting(Mockito.eq(10),Mockito.eq(5));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifmeeting"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifyou"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("user"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("meeting"));
		
		
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("/index.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}
	
	@Test
	public void testDoPostSuccessCaseYN() throws ServletException, IOException, SQLException {
		Mockito.when(request.getParameter("ifmeeting")).thenReturn("tak");
		Mockito.when(request.getParameter("ifyou")).thenReturn("nie");
		
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("meeting")).thenReturn(meeting);
        Mockito.when(user.getUser_id()).thenReturn(10);
        Mockito.when(meeting.getUserId()).thenReturn(15);
        Mockito.when(meeting.getId()).thenReturn(5);
		
		
		new SummaryServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(10),Mockito.eq(-1));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(15),Mockito.eq(1));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.scoreMeeting(Mockito.eq(10),Mockito.eq(5));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifmeeting"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifyou"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("user"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("meeting"));
		
		
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("/index.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}
	
	@Test
	public void testDoPostSuccessCaseYY() throws ServletException, IOException, SQLException {
		Mockito.when(request.getParameter("ifmeeting")).thenReturn("tak");
		Mockito.when(request.getParameter("ifyou")).thenReturn("tak");
		
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("meeting")).thenReturn(meeting);
        Mockito.when(user.getUser_id()).thenReturn(10);
        Mockito.when(meeting.getUserId()).thenReturn(15);
        Mockito.when(meeting.getId()).thenReturn(5);
		
		
		new SummaryServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(10),Mockito.eq(1));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.givePoints(Mockito.eq(15),Mockito.eq(1));
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.scoreMeeting(Mockito.eq(10),Mockito.eq(5));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifmeeting"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("ifyou"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("user"));
		(Mockito.verify(session, Mockito.times(1))).getAttribute(Mockito.eq("meeting"));
		
		
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("/index.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
	}

}
