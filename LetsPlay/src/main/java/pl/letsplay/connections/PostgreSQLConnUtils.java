package pl.letsplay.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa obsługująca połaczenie z bazą danych PostgreSQL
 * @author LU
 *
 */
public class PostgreSQLConnUtils {
	
	/**
	 * nazwa hosta z bazą danych
	 */
	private static String hostName = "ec2-54-75-239-190.eu-west-1.compute.amazonaws.com";
	/**
	 * nazwa bazy danych
	 */
	private static String database = "d9523h0gh40mno";
	/**
	 * nazwa użytkownika z dostępem do bazy danych
	 */
	private static String userName = "oxddgcpopdxqbv";
	/**
	 * numer portu do połaczenia z bazą danych
	 */
	private static String port = "5432";
	/**
	 * hasło użytkownika bazy danych
	 */
	private static String password = "abafdb6d66f34cc603e5d586481f7671373845e529e11929f097d734d3228272";
	    
	/**
	 * Metoda tworząca połaczenie z bazą danych dla domyślnych parametrów  z użyciem SSL
	 * @return połaczenie z bazą danych
	 * @throws SQLException
	 */
	public static Connection getPostgreSQLConnection() throws SQLException {
	     return getPostgreSQLConnection(hostName, port, database, userName, password);
	 }
	 
	/**
	 * Metoda tworząca połączenie z bazą danych z użyciem SSL
	 * @param hostName nazwa hosta
	 * @param port numer portu
	 * @param database nazwa bazy danych
	 * @param userName nazwa u�ytkownika
	 * @param password has�o u�ytkownika
	 * @return połaczenie z bazą danych
	 * @throws SQLException
	 */
	 public static Connection getPostgreSQLConnection(String hostName, String port, String database, String userName, String password) throws SQLException
	 {
		 try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("nie znaleziono org.postgresql.Driver");
		}
	     String connectionURL = "jdbc:postgresql://" + hostName + ":" + port + "/" + database;
	     
	     connectionURL = connectionURL + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	     
	     Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	     return conn;
	 }

}
