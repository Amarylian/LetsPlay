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
public class CreateMeetingIdeaServletTest {

	private HttpServletRequest request;       
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
	private User user;

	@Before
	public void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class);
        rd = Mockito.mock(RequestDispatcher.class);
        session = Mockito.mock(HttpSession.class);
        PowerMockito.mockStatic(DBUtils.class);
        user = Mockito.mock(User.class);
	}

	@Test
	public void testDoPostSuccess() throws ServletException, IOException, SQLException {
		Mockito.when(request.getParameter("city")).thenReturn("cityTest");
		Mockito.when(request.getParameter("attentions")).thenReturn("attentionsTest");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(user.getUser_id()).thenReturn(15);
        
        PowerMockito.when(DBUtils.createMeetingIdea(Mockito.eq(15),Mockito.eq("cityTest"),Mockito.eq("attentionsTest"))).thenReturn(new Idea());
		
		
		new CreateMeetingIdeaServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.createMeetingIdea(Mockito.eq(15),Mockito.eq("cityTest"),Mockito.eq("attentionsTest"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("city"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("attentions"));
		(Mockito.verify(user, Mockito.times(1))).getUser_id();
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("Index.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
		
	}
	
	@Test
	public void testDoPostFail() throws ServletException, IOException, SQLException {
		Mockito.when(request.getParameter("city")).thenReturn("cityTest");
		Mockito.when(request.getParameter("attentions")).thenReturn("attentionsTest");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(rd);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(user.getUser_id()).thenReturn(15);
        
        PowerMockito.when(DBUtils.createMeetingIdea(Mockito.eq(15),Mockito.eq("cityTest"),Mockito.eq("attentionsTest"))).thenReturn(null);
		
		
		new CreateMeetingIdeaServlet().doPost(request, response);
		
		PowerMockito.verifyStatic( Mockito.times(1)); // Verify that the following mock method was called exactly 1 time
		DBUtils.createMeetingIdea(Mockito.eq(15),Mockito.eq("cityTest"),Mockito.eq("attentionsTest"));
		
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("city"));
		(Mockito.verify(request, Mockito.times(1))).getParameter(Mockito.eq("attentions"));
		(Mockito.verify(user, Mockito.times(1))).getUser_id();
		(Mockito.verify(request, Mockito.times(1))).getRequestDispatcher(Mockito.eq("createMeetingIdea.jsp"));
		(Mockito.verify(rd, Mockito.times(1))).include(Mockito.any(ServletRequest.class), Mockito.any(ServletResponse.class));
		
	}

}
