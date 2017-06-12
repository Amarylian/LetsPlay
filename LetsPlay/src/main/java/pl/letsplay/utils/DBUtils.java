package pl.letsplay.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.letsplay.beans.Idea;
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
		String SQL = "SELECT * FROM data.users WHERE email='"+userEmail+"' AND password='"+password+"';";
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
        System.out.println(userEmail+" "+password);
        //if(user.getPassword().equals(password)) return null;
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
			stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
			stmt.close();
			
		} catch (SQLException e) {
			conn.close();
			return null;
		}
		
		System.out.println("Created user "+id);
		  
		conn.close();
		return findUser(id);
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
	  	public static Meeting createMeeting(int user_id, boolean priv, String city, String date, String time, String address, boolean addressVisible, int number, String attentions) throws SQLException {
		  
	  		Connection conn = ConnectionUtils.getConnection();
	  		int id = -1;
	  		try{
		  Statement stmt;
		  Meeting meeting = null;
			String fullDate = date + " " + time;
			String query = "INSERT INTO data.meetings(user_id,city,address,date,private,address_visible,max_players_number,attentions) VALUES('"+
					user_id+"','"+city+"','"+address+"',to_timestamp('"+fullDate+"','YYYY-MM-DD HH24:MI'),'"+priv+"','"+addressVisible+"','"+number+"','"+attentions+"');";
			stmt = conn.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
			String SQL = "INSERT INTO data.participation(user_id,meeting_id) VALUES('"+user_id+"','"+id+"');";
			System.out.println("DBUtilis:" + SQL);
			stmt.executeUpdate(SQL);
			
			
	  		}catch (SQLException e) {
				conn.close();
				return null;
			}
	  		conn.close();
		  
		  return findMeeting(id);
	  }
	  
	  	/**
	  	 * Wyszukiwanie spotkania po id
	  	 * @param id id spotkania
	  	 * @return znalezione spotkanie, null jeśli wystąpiła błąd
	  	 * @throws SQLException 
	  	 */
	  public static Meeting findMeeting(int id) throws SQLException{
		  
		  Connection conn = ConnectionUtils.getConnection();
		  Meeting meeting = null;
		Statement stmt;
		try {
			stmt = conn.createStatement();
		
			
			String SQL =  "SELECT * FROM data.meetings WHERE meeting_id='"+id+"';" ;
			System.out.println("DBUtils: "+SQL);
			
		    ResultSet rs = stmt.executeQuery(SQL);
		    if ( rs.next() ) {
		    	int meeting_id = rs.getInt("meeting_id");
		        boolean priv = rs.getBoolean("private");
		        String  city = rs.getString("city");
		        String address = rs.getString("address");
		        String fullDate = rs.getString("date");
		        boolean addressVisible = rs.getBoolean("address_visible");
		        int maxNumber = rs.getInt("max_players_number");
		        int actualNumber = rs.getInt("players_number");
		        String attentions = rs.getString("attentions");
		        //System.out.println("Meeting "+id+": "+date+" "+ad+", "+city);
		        
		        meeting = new Meeting(meeting_id, priv, city, fullDate.substring(0,9), fullDate.substring(11), address, addressVisible,
						actualNumber, maxNumber, attentions);
		    }
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		    
		    return meeting;
	  }
	  
	  /**
	   * Znalezienie wszystkich spotkanń
	   * @return lista spotkań, null w przypadku błędu
	 * @throws SQLException 
	   */
	  public static List<Meeting> queryMeeting() throws SQLException{
		  List<Meeting> res = new ArrayList<Meeting>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  try{
		  Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM data.meetings;" );
          while ( rs.next() ) {
             int id = rs.getInt("meeting_id");
             boolean priv = rs.getBoolean("private");
             String  city = rs.getString("city");
             String address = rs.getString("address");
             String fullDate = rs.getString("date");
             boolean addressVisible = rs.getBoolean("address_visible");
             int maxNumber = rs.getInt("max_players_number");
             int actualNumber = rs.getInt("players_number");
             String attentions = rs.getString("attentions");
             //System.out.println("Meeting "+id+": "+date+" "+ad+", "+city);
             
             res.add(new Meeting(id, priv, city, ((fullDate==null)?null:fullDate.substring(0,10)), 
            		 ((fullDate==null)?null:fullDate.substring(11)), address, addressVisible,
 					actualNumber, maxNumber, attentions));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  
	  /**
	   * 
	   * Znalezienie spotkań zgodnych z podanymi atrybutami
	   * @param attributes mapa atrybutów (keys: city, address, date_from, date_to, attentions)
	   * @return lista spotkań, null w przypadku błędu
	 * @throws SQLException 
	   */
	  public static List<Meeting> queryMeeting(Map<String, String> attributes) throws SQLException{
		  List<Meeting> res = new ArrayList<Meeting>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  
		  try{
		  Statement stmt = conn.createStatement();
		  String query = "SELECT * FROM data.meetings";
		  
		  if(!attributes.isEmpty()){
			  query = query + " WHERE ";
			  boolean first = true;
			  for(Map.Entry<String, String> entry : attributes.entrySet()){
				  if(!first) query = query + " AND ";
				  if(entry.getKey()=="date_from"){
					  query = query + " date >= to_timestamp('"+entry.getValue()+"','YYYY-MM-DD HH24:MI')";
				  }
				  else if(entry.getKey()=="date_to"){
					  query = query + " date <= to_timestamp('"+entry.getValue()+"','YYYY-MM-DD HH24:MI')";
				  }
				  else{
					  query = query + entry.getKey() + " LIKE '%" + entry.getValue() + "%'";
				  }
				  first = false;
			  }
		  }
			  
		  query = query + ";";
		  
		  System.out.println("DBUtils: "+query);
		  
          ResultSet rs = stmt.executeQuery(query);
          while ( rs.next() ) {
             int id = rs.getInt("meeting_id");
             boolean priv = rs.getBoolean("private");
             String  city = rs.getString("city");
             String address = rs.getString("address");
             String fullDate = rs.getString("date");
             boolean addressVisible = rs.getBoolean("address_visible");
             int maxNumber = rs.getInt("max_players_number");
             int actualNumber = rs.getInt("players_number");
             String attentions = rs.getString("attentions");
             //System.out.println("Meeting "+id+": "+date+" "+ad+", "+city);
             
             res.add(new Meeting(id, priv, city, ((fullDate==null)?null:fullDate.substring(0,10)), 
            		 ((fullDate==null)?null:fullDate.substring(11)), address, addressVisible,
 					actualNumber, maxNumber, attentions));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  

	  /**
	   * Tworzenie nowego pomysłu
	   * @param user_id id twórcy pomysłu
	   * @param city miasto
	   * @param attentions uwagi dodatkowe
	   * @return utworzony pomysł
	 * @throws SQLException bład bazy danych
	   */
	  public static Idea createMeetingIdea(int user_id,String city, String attentions) throws SQLException{
		  Connection conn = ConnectionUtils.getConnection();
	  		int id = -1;
	  		try{
		  Statement stmt;
		  Idea idea = null;
			String query = "INSERT INTO data.ideas(user_id,city,attentions) VALUES('"+
					user_id+"','"+city+"','"+attentions+"');";
			stmt = conn.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
			System.out.println("DBUtilis:" + query);
			
			
	  		}catch (SQLException e) {
				conn.close();
				return null;
			}
	  		conn.close();
		  
		  return findIdea(id);
	  }

	  /**
	   * Znalezienie spotkań, w których uczestnik wziął udział, ale jeszcze ich nie ocenił
	   * @param user zalogowany użytkownik
	   * @return lista spotkań
	   * @throws SQLException
	   */
	  public static List<Meeting> queryMeeting(User user) throws SQLException{
		  List<Meeting> res = new ArrayList<Meeting>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  try{
		  Statement stmt = conn.createStatement();
		  int user_id = user.getUser_id();
		  ResultSet rs = stmt.executeQuery( "SELECT * FROM data.meetings m JOIN data.participation p USING(meeting_id) WHERE p.user_id="+user_id+
		   "AND p.finished = false");
          while ( rs.next() ) {
             int id = rs.getInt("meeting_id");
             boolean priv = rs.getBoolean("private");
             String  city = rs.getString("city");
             String address = rs.getString("address");
             String fullDate = rs.getString("date");
             boolean addressVisible = rs.getBoolean("address_visible");
             int maxNumber = rs.getInt("max_players_number");
             int actualNumber = rs.getInt("players_number");
             String attentions = rs.getString("attentions");
             //System.out.println("Meeting "+id+": "+date+" "+ad+", "+city);
             
             res.add(new Meeting(id, priv, city, ((fullDate==null)?null:fullDate.substring(0,10)), 
            		 ((fullDate==null)?null:fullDate.substring(11)), address, addressVisible,
 					actualNumber, maxNumber, attentions));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  
	  /**
	   * Dołączenie do spotkania
	   * @param meeting spotkanie, do którego chcemy dołączyć
	   * @param user zalogowany użytkownik
	   * @throws SQLException błąd bazy danych
	   */
	  public static void joinMeeting(Meeting meeting, User user) throws SQLException {
		  Connection conn = ConnectionUtils.getConnection();
		  Statement stmt;
		  int user_id = user.getUser_id();
		  int meeting_id = meeting.getId();
			String SQL = "INSERT INTO data.participation(user_id,meeting_id) VALUES('"+user_id+"','"+meeting_id+"');";
			stmt = conn.createStatement();
			System.out.println("DBUtilis:" + SQL);
			stmt.executeUpdate(SQL);
			
			stmt.close();
			conn.close();
	  }
	  
	  /**
	   * Poszukiwanie pomysłu po id
	   * @param id id pomysłu
	   * @return znaleziony po
	   * @throws SQLException błąd bazy danych
	   */
	  public static Idea findIdea(int id) throws SQLException{
		  
		  Connection conn = ConnectionUtils.getConnection();
		  Idea idea = null;
		Statement stmt;
		try {
			stmt = conn.createStatement();
		
			
			String SQL =  "SELECT * FROM data.ideas WHERE idea_id='"+id+"';" ;
			System.out.println("DBUtils: "+SQL);
			
		    ResultSet rs = stmt.executeQuery(SQL);
		    if ( rs.next() ) {
		    	int idea_id = rs.getInt("idea_id");
		        String  city = rs.getString("city");
		        String attentions = rs.getString("attentions");
		        int meeting_id = rs.getInt("meeting_id");
		        int user_id = rs.getInt("user_id");
		        
		        idea = new Idea(idea_id, city, attentions, user_id, meeting_id);
		    }
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		    
		    return idea;
	  }
	  
	  /**
	   * Szukanie spotkań, których organizatorem jest zalogowany użytkownik
	   * @param user zalogowany użytkownik
	   * @return lista spotkań użytkownika
	   * @throws SQLException błąd bazy danych
	   */
	  public static List<Meeting> myMeeting(User user) throws SQLException{
		  List<Meeting> res = new ArrayList<Meeting>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  try{
		  Statement stmt = conn.createStatement();
		  int user_id = user.getUser_id();
		  ResultSet rs = stmt.executeQuery( "SELECT * FROM data.meetings WHERE user_id="+user_id);
          while ( rs.next() ) {
             int id = rs.getInt("meeting_id");
             boolean priv = rs.getBoolean("private");
             String  city = rs.getString("city");
             String address = rs.getString("address");
             String fullDate = rs.getString("date");
             boolean addressVisible = rs.getBoolean("address_visible");
             int maxNumber = rs.getInt("max_players_number");
             int actualNumber = rs.getInt("players_number");
             String attentions = rs.getString("attentions");
             //System.out.println("Meeting "+id+": "+date+" "+ad+", "+city);
             
             res.add(new Meeting(id, priv, city, ((fullDate==null)?null:fullDate.substring(0,10)), 
            		 ((fullDate==null)?null:fullDate.substring(11)), address, addressVisible,
 					actualNumber, maxNumber, attentions));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  
	  /**
	   * Zwrócenie listy pomysłów
	   * @return list pomysłów
	   * @throws SQLException
	   */
	  public static List<Idea> queryIdeas() throws SQLException{
		  List<Idea> res = new ArrayList<Idea>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  try{
		  Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM data.ideas;" );
          while ( rs.next() ) {
             int meeting_id = rs.getInt("meeting_id");
             String  city = rs.getString("city");
             String attentions = rs.getString("attentions");
             int user_id = rs.getInt("user_id");
             int idea_id = rs.getInt("idea_id");
             
             res.add(new Idea(idea_id, city, attentions, user_id, meeting_id));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  
	  /**
	   * Zwrócenie listy pomysłów, które nie mają utworzonego spotkania
	   * @return list pomysłów
	   * @throws SQLException
	   */
	  public static List<Idea> queryIdeasWithoutMeetings() throws SQLException{
		  List<Idea> res = new ArrayList<Idea>();
		  
		  Connection conn = ConnectionUtils.getConnection();
		  
		  try{
		  Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM data.ideas where meeting_id IS NOT NULL;" );
          while ( rs.next() ) {
             int meeting_id = rs.getInt("meeting_id");
             String  city = rs.getString("city");
             String attentions = rs.getString("attentions");
             int user_id = rs.getInt("user_id");
             int idea_id = rs.getInt("idea_id");
             
             if(meeting_id!=0) res.add(new Idea(idea_id, city, attentions, user_id, meeting_id));
          }
          rs.close();
          stmt.close();
          conn.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		  return res;
	  }
	  
}
