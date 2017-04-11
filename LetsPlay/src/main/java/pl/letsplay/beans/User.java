package pl.letsplay.beans;

public class User {
	
	private int user_id;
	private String email;
	private String name;
	private String surname;
	private String nick;
	private String password;
	
	public User(){
		this.user_id = -1;
		this.email = null;
		this.name = null;
		this.surname = null;
		this.nick = null;
		this.password = null;
	}
	
	public User(int id, String email, String name, String surname, String nick, String password){
		this.user_id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.nick = nick;
		this.password = password;
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

}
