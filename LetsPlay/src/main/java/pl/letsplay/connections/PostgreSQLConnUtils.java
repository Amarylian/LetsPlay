package pl.letsplay.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa obs³uguj¹ca po³aczenie z baz¹ danych PostgreSQL
 * @author LU
 *
 */
class PostgreSQLConnUtils {
	
	/**
	 * nazwa hosta z baz¹ danych
	 */
	private static String hostName = "ec2-54-75-239-190.eu-west-1.compute.amazonaws.com";
	/**
	 * nazwa bazy danych
	 */
	private static String database = "d9523h0gh40mno";
	/**
	 * nazwa u¿ytkownika z dostêpem do bazy danych
	 */
	private static String userName = "oxddgcpopdxqbv";
	/**
	 * numer portu do po³aczenia z baz¹ danych
	 */
	private static String port = "5432";
	/**
	 * has³o u¿ytkownika bazy danych
	 */
	private static String password = "abafdb6d66f34cc603e5d586481f7671373845e529e11929f097d734d3228272";
	    
	/**
	 * Metoda tworz¹ca po³aczenie z baz¹ danych
	 * @return po³aczenie z baz¹ danych
	 * @throws SQLException
	 */
	public static Connection getPostgreSQLConnection() throws SQLException {
	     return getPostgreSQLConnection(hostName, port, database, userName, password);
	 }
	 
	/**
	 * Metoda tworz¹ca po³¹czenie z baz¹ danych
	 * @param hostName nazwa hosta
	 * @param port numer portu
	 * @param database nazwa bazy danych
	 * @param userName nazwa u¿ytkownika
	 * @param password has³o u¿ytkownika
	 * @return po³aczenie z baz¹ danych
	 * @throws SQLException
	 */
	 private static Connection getPostgreSQLConnection(String hostName, String port, String database, String userName, String password) throws SQLException
	 {
	     String connectionURL = "jdbc:postgresql://" + hostName + ":" + port + "/" + database;
	     Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	     return conn;
	 }

}
