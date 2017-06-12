package pl.letsplay.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IdeaTests {
	private Idea emptyIdea = null;
	private Idea idea = null;
	private Idea emptyCityIdea = null;
	
	@Before
	public void setUp() throws Exception {
		this.emptyIdea = new Idea();
		//	int id, String city, String attentions, int user_id, int meeting_id;
		this.emptyCityIdea = new Idea(5,null,"uwagi", 2,1);
		this.idea = new Idea(5,"Poznan", "uwagi", 2,1);
	}

	@Test
	public void testGetIdea_id() {
		assertEquals(-1, this.emptyIdea.getIdea_id());
		assertEquals(5, this.emptyCityIdea.getIdea_id());
		assertEquals(5, this.idea.getIdea_id());
	}

	@Test
	public void testSetIdea_id() {
		this.emptyIdea.setIdea_id(5);
		assertEquals(5, this.emptyIdea.getIdea_id());
	}

	@Test
	public void testGetCity() {
		this.emptyIdea.setCity("Poznan");
		assertEquals("Poznan", this.emptyIdea.getCity());
	}

	@Test
	public void testSetCity() {
		assertEquals(null, this.emptyIdea.getCity());
		assertEquals(null, this.emptyCityIdea.getCity());
		assertEquals("Poznan", this.idea.getCity());
	}

	@Test
	public void testGetAttentions() {
		assertEquals(null, this.emptyIdea.getAttentions());
		assertEquals("uwagi", this.emptyCityIdea.getAttentions());
		assertEquals("uwagi", this.idea.getAttentions());
	}

	@Test
	public void testSetAttentions() {
		this.emptyIdea.setAttentions("uwagi");
		assertEquals("uwagi", this.emptyIdea.getAttentions());
	}

	@Test
	public void testGetUser_id() {
		assertEquals(-1, this.emptyIdea.getUser_id());
		assertEquals(2, this.emptyCityIdea.getUser_id());
		assertEquals(2, this.idea.getUser_id());
	}

	@Test
	public void testSetUser_id() {
		this.emptyIdea.setUser_id(2);
		assertEquals(2, this.emptyIdea.getUser_id());
	}

	@Test
	public void testGetMeeting_id() {
		assertEquals(-1, this.emptyIdea.getMeeting_id());
		assertEquals(1, this.emptyCityIdea.getMeeting_id());
		assertEquals(1, this.idea.getMeeting_id());
	}

	@Test
	public void testSetMeeting_id() {
		this.emptyIdea.setMeeting_id(2);
		assertEquals(2, this.emptyIdea.getMeeting_id());
	}

}
