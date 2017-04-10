package pl.letsplay.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.letsplay.beans.User;


public class DBUtils {
	
	public static User findUser(Connection conn, String userEmail, String password) throws SQLException {
		 
	      return null;
	  }
	 
	  public static User findUser(Connection conn, String userName) throws SQLException {
	 
		  return null;
	  }
	 
	  public static List<User> queryUser(Connection conn) throws SQLException {
	      
		  return null;
	  }

	  public static User registerUser(Connection conn, String login, String name, String surname, String email, String password) throws SQLException {
		  //Zwraca null jak się nie uda dodać a jak się uda to zwraca tego użytkownika
		  
		  return null;
	  }
	  
	  public static int createMeeting(Connection conn,	String priv, String city, String date, String time, String address, String address2, String number, String attentions) throws SQLException {
		  //w sumie nie wiem co ma zwracać - rejestracja spotkania w bazie
		  
		  return 0;
	  }
}
