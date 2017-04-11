package pl.letsplay.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.letsplay.beans.Meeting;
import pl.letsplay.beans.User;
import pl.letsplay.connections.ConnectionUtils;

/**
 * Klasa wysyłająca zapytania (find, create, query) do bazy danych
 * @author LU
 *
 */
public class DBUtils {
	
	/**
	 * Szukanie uzytkownika po emailu i haśle
	 * @param userEmail email użytkownika
	 * @param password hasło do logowania
	 * @return znaleziony uzytkownik; null w przypadku braku dopasowania w bazie
	 * @throws SQLException
	 */
	public static User findUser(String userEmail, String password) throws SQLException {
		Connection conn = ConnectionUtils.getConnection();
		User user = null;
		Statement stmt = conn.createStatement();
		String SQL = "SELECT * FROM data.users WHERE email='"+userEmail+"';";
		System.out.println("DBUtils: "+SQL);
        ResultSet rs = stmt.executeQuery(SQL);
        if ( rs.next() ) {
           int id = rs.getInt("user_id");
           String  email = rs.getString("email");
           String name = rs.getString("name");
           String surname = rs.getString("surname");
           String nick = rs.getString("nick");
           String pass = rs.getString("password");
           user = new User(id, email, name, surname, nick, pass);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        System.out.println("Finded user "+user);
        
	    return user;
	  }
	 
	/**
	 * Szukanie użytkownika po emailu
	 * @param email email szukanego użytkownika
	 * @return znaleziony uzytkownik (w przypadku wielu dopasowań jeden losowy); null w przypadku braku dopasowania
	 * @throws SQLException
	 */
	  public static User findUser(String email) throws SQLException {
		  Connection conn = ConnectionUtils.getConnection();
		  User user = null;
		  Statement stmt = conn.createStatement();
		  
		  String SQL = "SELECT * FROM data.users WHERE email='"+email+"';";
		  System.out.println("DBUtils: "+SQL);
		  ResultSet rs = stmt.executeQuery(SQL);
		  
	      if ( rs.next() ) {
	           int user_id = rs.getInt("user_id");
	           String  mail = rs.getString("email");
	           String name = rs.getString("name");
	           String surname = rs.getString("surname");
	           String nick = rs.getString("nick");
	           String pass = rs.getString("password");
	           user = new User(user_id, mail, name, surname, nick, pass);
	        }
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	        
	        
	        System.out.println("Finded user "+user);
	        
		    return user;

	  }
	  
	  /**
	   * Szukanie uzytkownika po id
	   * @param id id użytkownika
	   * @return znaleziony uzytkownik (w przypadku wielu dopasowań jeden losowy); null w przypadku braku dopasowania
	   * @throws SQLException
	   */
	  public static User findUser(int id) throws SQLException {
		  Connection conn = ConnectionUtils.getConnection();
			User user = null;
			Statement stmt = conn.createStatement();
			
			String SQL =  "SELECT * FROM data.users WHERE user_id='"+id+"';" ;
			System.out.println("DBUtils: "+SQL);
			
	        ResultSet rs = stmt.executeQuery(SQL);
	        if ( rs.next() ) {
	           int user_id = rs.getInt("user_id");
	           String  email = rs.getString("email");
	           String name = rs.getString("name");
	           String surname = rs.getString("surname");
	           String nick = rs.getString("nick");
	           String pass = rs.getString("password");
	           user = new User(user_id, email, name, surname, nick, pass);
	        }
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	        
	        System.out.println("Finded user "+user);
	        
		    return user;
		  }
	 
	  /**
	   * Znajduje wszystkich użytkowników
	   * @return lista wszystkich użytkowników
	   * @throws SQLException
	   */
	  public static List<User> queryUser() throws SQLException {
		  Connection conn = ConnectionUtils.getConnection();
		  List<User> res = new ArrayList<User>();
		  Statement stmt = conn.createStatement();
		  
		  String SQL =   "SELECT * FROM data.users;" ;
		System.out.println("DBUtils: "+SQL);
		  
          ResultSet rs = stmt.executeQuery(SQL);
          while ( rs.next() ) {
             int id = rs.getInt("user_id");
             String  email = rs.getString("email");
             String name = rs.getString("name");
             String surname = rs.getString("surname");
             String nick = rs.getString("nick");
             String pass = rs.getString("password");
             User u = new User(id, email, name, surname, nick, pass);
             res.add(u);
          }
          rs.close();
          stmt.close();
          conn.close();
          
          System.out.println("Finded users :");
          for(User u: res) System.out.println(u);
	      
		  return res;
	  }

	  /**
	   * Tworzenie użytkownika
	   * @param login nick użytkownika
	   * @param name imię użytkownika
	   * @param surname nazwisko użytkownika
	   * @param email email uzytkownika
	   * @param password hasło do logowania
	   * @return utworzony uzytkownik; null w przypadku niepowodzenia
	   * @throws SQLException
	   */
	  public static User createUser(String nick, String name, String surname, String email, String password) throws SQLException {
		  Connection conn = ConnectionUtils.getConnection();
		  Statement stmt;
		  int id = 0;
		  User user = null;
		try {
			String SQL = "INSERT INTO data.users(email,name,surname,password,nick) VALUES('"+email+"','"+name+"','"+surname+"','"+password+"','"+nick+"');";
			stmt = conn.createStatement();
			System.out.println("DBUtilis:" + SQL);
			id = stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
			stmt.close();
			
		} catch (SQLException e) {
			conn.close();
			return null;
		}
		
		System.out.println("Created user "+email);
		  
		conn.close();
		return findUser(email);
	  }
	  
	  /**
	   * Utworzenie spotkania
	   * @param priv widoczności spotkania
	   * @param city miasto
	   * @param date data spotkania
	   * @param time godzina spotkania
	   * @param address miejsce spotkania
	   * @param address2 miejsce spotkania
	   * @param number maksymalna liczba graczy
	   * @param attentions
	   * @return utworzone spotkanie; null w przypadku niepowodzenia
	   * @throws SQLException
	   */
	  public static Meeting createMeeting(String priv, String city, String date, String time, String address, String address2, String number, String attentions) throws SQLException {
		  //Connection conn = ConnectionUtils.getConnection();
		  
		  System.out.println(priv+"   "+city+"   "+date+"   "+time+"  "+address+"   "+ address2+"   "+ number+"   "+attentions);
		  

		  
		  return null;
	  }
}
