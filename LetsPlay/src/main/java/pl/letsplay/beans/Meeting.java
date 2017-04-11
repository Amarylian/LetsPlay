package pl.letsplay.beans;

public class Meeting {
	private boolean priv;
	private String city;
	private String date;
	private String time;
	private String address;
	private boolean addressVisible;
	private int maxNumber;
	private int actualNumber;
	private String attentions;
	
	
	public Meeting(boolean priv, String city, String date, String time, String address, boolean addressVisible,
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
	}
	
	public Meeting(){
		
	}
	
	
	

}
