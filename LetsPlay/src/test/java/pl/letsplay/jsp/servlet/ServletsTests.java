package pl.letsplay.jsp.servlet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateMeetingIdeaServletTest.class, GetAllMeetingsServletTest.class, GetMyMeetingsServletTest.class,
		LoginServletTest.class, PasswordReminderServletTest.class, ShowMeetingServletTest.class,
		SummaryServletTest.class })
public class ServletsTests {

}
