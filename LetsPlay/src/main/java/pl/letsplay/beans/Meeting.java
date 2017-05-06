package pl.letsplay.beans;

public class Meeting {
	private int id;
	private boolean priv;
	private String city;
	private String date;
	private String time;
	private String address;
	private boolean addressVisible; 
	private int maxNumber;
	private int actualNumber;
	private String attentions;
	
	
	public Meeting(int id, boolean priv, String city, String date, String time, String address, boolean addressVisible,
			int actualNumber, int maxNumber, String attentions) {
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
	
	
	

}
