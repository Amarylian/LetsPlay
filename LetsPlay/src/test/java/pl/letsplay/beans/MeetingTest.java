package pl.letsplay.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MeetingTest {
	private Meeting emptyMeeting = null;
	private Meeting emptyCityMeeting = null;
	private Meeting meeting = null;
	
	@Before
	public void setUp() throws Exception {
		this.emptyMeeting = new Meeting();
		//	int id, boolean priv, String city, String date, String time, String address, boolean addressVisible,int maxNumber;
		//int actualNumber,String attentions;
		this.emptyCityMeeting = new Meeting(5,false,null,"2000-12-03", "12:30", "Piotrowo 2", true, 3,5, "uwagi",3);
		this.meeting = new Meeting(5,false,"Poznan","2000-12-03", "12:30", "Piotrowo 2", true, 3,5, "uwagi",3);
	}
	@Test
	public void testIsPriv() {
		assertEquals(false, this.emptyMeeting.isPriv());
		assertEquals(false, this.emptyCityMeeting.isPriv());
		assertEquals(false, this.meeting.isPriv());
	}

	@Test
	public void testSetPriv() {
		this.emptyMeeting.setPriv(true);
		assertEquals(true, this.emptyMeeting.isPriv());
	}

	@Test
	public void testGetCity() {
		assertEquals(null, this.emptyMeeting.getCity());
		assertEquals(null, this.emptyCityMeeting.getCity());
		assertEquals("Poznan", this.meeting.getCity());
	}

	@Test
	public void testSetCity() {
		this.emptyMeeting.setCity("Poznan");
		assertEquals("Poznan", this.emptyMeeting.getCity());
	}

	@Test
	public void testGetDate() {
		assertEquals(null, this.emptyMeeting.getDate());
		assertEquals("2000-12-03", this.emptyCityMeeting.getDate());
		assertEquals("2000-12-03", this.meeting.getDate());
	}

	@Test
	public void testSetDate() {
		this.emptyMeeting.setDate("2000-12-03");
		assertEquals("2000-12-03", this.emptyMeeting.getDate());
	}

	@Test
	public void testGetTime() {
		assertEquals(null, this.emptyMeeting.getTime());
		assertEquals("12:30", this.emptyCityMeeting.getTime());
		assertEquals("12:30", this.meeting.getTime());
	}

	@Test
	public void testSetTime() {
		this.emptyMeeting.setTime("12:30");
		assertEquals("12:30", this.emptyMeeting.getTime());
	}

	@Test
	public void testGetAddress() {
		assertEquals(null, this.emptyMeeting.getAddress());
		assertEquals("Piotrowo 2", this.emptyCityMeeting.getAddress());
		assertEquals("Piotrowo 2", this.meeting.getAddress());
	}

	@Test
	public void testSetAddress() {
		this.emptyMeeting.setAddress("Piotrowo 2");
		assertEquals("Piotrowo 2", this.emptyMeeting.getAddress());
	}

	@Test
	public void testIsAddressVisible() {
		assertEquals(false, this.emptyMeeting.isAddressVisible());
		assertEquals(true, this.emptyCityMeeting.isAddressVisible());
		assertEquals(true, this.meeting.isAddressVisible());
	}

	@Test
	public void testSetAddressVisible() {
		this.emptyMeeting.setAddressVisible(true);
		assertEquals(true, this.emptyMeeting.isAddressVisible());
	}

	@Test
	public void testGetMaxNumber() {
		assertEquals(0, this.emptyMeeting.getMaxNumber());
		assertEquals(5, this.emptyCityMeeting.getMaxNumber());
		assertEquals(5, this.meeting.getMaxNumber());
	}

	@Test
	public void testSetMaxNumber() {
		this.emptyMeeting.setMaxNumber(5);
		assertEquals(5, this.emptyMeeting.getMaxNumber());
	}

	@Test
	public void testGetActualNumber() {
		assertEquals(0, this.emptyMeeting.getActualNumber());
		assertEquals(3, this.emptyCityMeeting.getActualNumber());
		assertEquals(3, this.meeting.getActualNumber());
	}

	@Test
	public void testSetActualNumber() {
		this.emptyMeeting.setActualNumber(3);
		assertEquals(3, this.emptyMeeting.getActualNumber());
	}

	@Test
	public void testGetAttentions() {
		assertEquals(null, this.emptyMeeting.getAttentions());
		assertEquals("uwagi", this.emptyCityMeeting.getAttentions());
		assertEquals("uwagi", this.meeting.getAttentions());
	}

	@Test
	public void testSetAttentions() {
		this.emptyMeeting.setAttentions("uwagi");
		assertEquals("uwagi", this.emptyMeeting.getAttentions());
	}
	@Test
	public void testGetId() {
		assertEquals(0, this.emptyMeeting.getId());
		assertEquals(5, this.emptyCityMeeting.getId());
		assertEquals(5, this.meeting.getId());
	}
	@Test
	public void testSetId() {
		this.emptyMeeting.setId(5);
		assertEquals(5, this.emptyMeeting.getId());
	}

}
