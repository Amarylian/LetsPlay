package pl.letsplay.beans;

/**
 * Klasa będąca odzwierciedleniem rekordów z tabeli data.meetings
 * @author LU
 *
 */
public class Meeting {

	/**
	 * id spotkania (unikatowe)
	 */
	private int id;
	/**
	 * czy spotkanie jest prywatne (widoczne tylko dla zaproszonych użytkowników)
	 */
	private boolean priv;
	/**
	 * miasto, w którym odbywa się spotkanie
	 */
	private String city;
	/**
	 * data spotkania (DD-MM-YYYY)
	 */
	private String date;
	/**
	 * godzina spotkania (HH24:MI)
	 */
	private String time;
	/**
	 * adres
	 */
	private String address;
	/**
	 * czy adres jest widoczny dla wszystkich uzytkowników
	 */
	private boolean addressVisible; 
	/**
	 * maksymalna liczba graczy mogących wziąć udział w spotkaniu
	 */
	private int maxNumber;
	/**
	 * aktualna liczba graczy biorących udział w spotkaniu
	 */
	private int actualNumber;
	/**
	 * dodatkowe uwagi 
	 */
	private String attentions;
	/**
	 * id twórcy spotkania
	 */
	private int user_id;
	
	/**
	 * 
	 * @param id id
	 * @param priv czy spotkanie prywatne
	 * @param city miasto
	 * @param date dzień spotkanie
	 * @param time godzina spotkania
	 * @param address adres
	 * @param addressVisible widoczność adresu
	 * @param actualNumber aktualna liczba graczy
	 * @param maxNumber maksymalna liczba graczy
	 * @param attentions uwagi dodatkowe
	 */
	public Meeting(int id, boolean priv, String city, String date, String time, String address, boolean addressVisible,
			int actualNumber, int maxNumber, String attentions, int user_id) {
		super();
		this.priv = priv;
		this.city = city;
		this.date = date;
		this.time = time;
		this.address = address;
		this.addressVisible = addressVisible;
		this.actualNumber = actualNumber;
		this.maxNumber = maxNumber;
		this.attentions = attentions;
		this.id = id;
		this.user_id = user_id;
	}
	
	public String toString(){
		return "("+id+") "+address+" "+city+", ("+addressVisible+"), widoczne: "+priv+", data: "+time+" "+date+", "+actualNumber+"/"+
				maxNumber+", "+attentions;
	}
	
	public boolean isPriv() {
		return priv;
	}

	public void setPriv(boolean priv) {
		this.priv = priv;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAddressVisible() {
		return addressVisible;
	}

	public void setAddressVisible(boolean addressVisible) {
		this.addressVisible = addressVisible;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(int actualNumber) {
		this.actualNumber = actualNumber;
	}

	public String getAttentions() {
		return attentions;
	}

	public void setAttentions(String attentions) {
		this.attentions = attentions;
	}

	public Meeting(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return user_id;
	}

	public void setUserId(int id) {
		this.user_id = id;
	}
	
	
	

}
