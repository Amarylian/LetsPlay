package pl.letsplay.jsp.servlet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginServletTest.class, PasswordReminderServletTest.class, GetAllMeetingsServletTest.class })
public class ServletsTests {

}
