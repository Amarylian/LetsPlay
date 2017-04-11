package pl.letsplay.connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Klasa obsługująca połaczenie bazą danych (dowolny typ)
 * @author LU
 *
 */
public class ConnectionUtils {
	/**
	 * Utworzenie połaczenia z bazą danych
	 * @return połaczenie z bazą
	 * @throws SQLException bład podczas nawiązywania połaczenia
	 */
	public static Connection getConnection() throws SQLException {
     
      // Here I using Oracle Database.
      return PostgreSQLConnUtils.getPostgreSQLConnection();
  }
   /**
    * Zamknięcie połaczenia z bazą danych
    * @param conn aktywne połaczenia
    */
  public static void closeQuietly(Connection conn) {
      try {
          conn.close();
      } catch (Exception e) {
      }
  }
/**
 * wycofanie transakcji w ramach aktywnego połączenia
 * @param conn aktywne połaczenie
 */
  public static void rollbackQuietly(Connection conn) {
      try {
          conn.rollback();
      } catch (Exception e) {
      }
  }

}
