package pl.letsplay.beans;

/**
 * Klasa będąca kontenerem dla rekordów tabeli data.users
 * @author LU
 *
 */
public class User {
	
	/**
	 * id użytkownika (unikatowe)
	 */
	private int user_id;
	/**
	 * email użytkownika (unikatowy)
	 */
	private String email;
	/**
	 * imię użytkownika
	 */
	private String name;
	/**
	 * nazwisko użytkownika
	 */
	private String surname;
	/**
	 * nick użytkownika
	 */
	private String nick;
	/**
	 * hasło do konta
	 */
	private String password;
	
	private int points;
	
	public User(){
		this.user_id = -1;
		this.email = null;
		this.name = null;
		this.surname = null;
		this.nick = null;
		this.password = null;
		this.setPoints(0);
	}
	/**
	 * 
	 * @param id id
	 * @param email adres email
	 * @param name imię
	 * @param surname nazwisko
	 * @param nick pseudonim
	 * @param password hasło do konta
	 */
	public User(int id, String email, String name, String surname, String nick, String password, int points){
		this.user_id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.nick = nick;
		this.password = password;
		this.points = points;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		String res = "User "+user_id+": "+email+" ("+name;
		if(nick!=null) res = res + " '"+nick+"'";
		res = res + " " + surname + " ["+password+"]";
		return res;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

}
