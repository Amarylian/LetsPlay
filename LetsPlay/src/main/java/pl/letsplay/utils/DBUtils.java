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
	 * @throws SQLException błąd podczas połaczenia z bazą danych
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
	 * @throws SQLException błąd podczas połaczenia z bazą danych
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
	   * @throws SQLException błąd podczas połaczenia z bazą danych
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
	   * @throws SQLException błąd podczas połaczenia z bazą danych
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
	   * @param nick nick użytkownika
	   * @param name imię użytkownika
	   * @param surname nazwisko użytkownika
	   * @param email email uzytkownika
	   * @param password hasło do logowania
	   * @return utworzony uzytkownik; null w przypadku niepowodzenia
	   * @throws SQLException błąd podczas połaczenia z bazą danych
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
	   * Tworzenie spotkania
	   * @param priv widoczność spotkania (true, jeśli prywatne)
	   * @param city miasto spotkania
	   * @param date data spotkania w formacie 'YYYY-MM-DD'
	   * @param time godzina spotkania w formacie 'HH24:MI'
	   * @param address adres spotkania
	   * @param addressVisible widoczność adresu (true, jeśli widoczny dla wszystkich)
	   * @param number maksymalna liczba graczy
	   * @param attentions uwagi do spotkania
	   * @return utworzone spotkanie; null jeśli wystąpiła błąd
	   * @throws SQLException błąd podczas połaczenia z bazą danych
	   */
	  	public static Meeting createMeeting(boolean priv, String city, String date, String time, String address, boolean addressVisible, int number, String attentions) throws SQLException {
		  
	  		Connection conn = ConnectionUtils.getConnection();
	  		try{
		  Statement stmt;
		  int id = 0;
		  Meeting meeting = null;
			String fullDate = date + " " + time;
			String query = "INSERT INTO data.meetings(city,address,date,private,address_visible,max_players_number,attentions) VALUES('"+
					city+"','"+address+"',to_timestamp('"+fullDate+"','YYYY-MM-DD HH24:MI'),'"+priv+"','"+addressVisible+"','"+number+"','"+attentions+"');";
			stmt = conn.createStatement();
			id = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			stmt.close();
	  		}catch (SQLException e) {
				conn.close();
				return null;
			}
	  		conn.close();
		  
		  return new Meeting();
	  }
}
