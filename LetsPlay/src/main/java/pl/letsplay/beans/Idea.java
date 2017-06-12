package pl.letsplay.beans;


/**
 * Klasa będąca odzwierciedleniem rekordów z tabeli data.ideas
 * @author LU
 *
 */

public class Idea {
	/**
	 * id pomysłu (unikatowe)
	 */
	private int idea_id;
	
	/**
	 * miasto, w którym ma odbyć się pomysł
	 */
	private String city;
	
	/**
	 * uwagi do pomysłu
	 */
	private String attentions;
	
	/**
	 * id użytkownika, który wymyślił pomysł
	 */
	private int user_id;
	
	/**
	 * id spotkania, które zostało utworzone z pomysłu
	 */
	private int meeting_id;

	public Idea() {
		this.idea_id = -1;
		this.city = null;
		this.attentions = null;
		this.user_id = -1;
		this.meeting_id = -1;
	}
	
	public Idea(int idea_id, String city, String attentions, int user_id, int meeting_id) {
		this.idea_id = idea_id;
		this.city = city;
		this.attentions = attentions;
		this.user_id = user_id;
		this.meeting_id = meeting_id;
	}
	
	
	public int getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAttentions() {
		return attentions;
	}

	public void setAttentions(String attentions) {
		this.attentions = attentions;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMeeting_id() {
		return meeting_id;
	}

	public void setMeeting_id(int meeting_id) {
		this.meeting_id = meeting_id;
	}
}
