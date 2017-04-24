package pl.letsplay.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.letsplay.beans.User;

public class UserTest {
	private User emptyUser = null;
	private User emptyNickUser = null;
	private User user = null;
	
	@Before
	public void setUp() throws Exception {
		this.emptyUser = new User();
		//int id, String email, String name, String surname, String nick, String password
		this.emptyNickUser = new User(0, "test@test.com", "nameTest", "surnameTest", null, "passwordTest");
		this.user = new User(0, "test@test.com", "nameTest", "surnameTest", "nickTest", "passwordTest");
	}
	
	@Test
	public void testGetUser_id() {
		assertEquals(-1, this.emptyUser.getUser_id());
		assertEquals(0, this.emptyNickUser.getUser_id());
		assertEquals(0, this.user.getUser_id());
	}
	
	@Test
	public void testSetUser_id() {
		this.emptyUser.setUser_id(100);
		assertEquals(100, this.emptyUser.getUser_id());
	}

	@Test
	public void testGetEmail() {
		assertEquals(null, this.emptyUser.getEmail());
		assertEquals("test@test.com", this.emptyNickUser.getEmail());
		assertEquals("test@test.com", this.user.getEmail());
	}

	@Test
	public void testSetEmail() {
		this.emptyUser.setEmail("test");
		assertEquals("test", this.emptyUser.getEmail());
	}

	@Test
	public void testGetName() {
		assertEquals(null, this.emptyUser.getName());
		assertEquals("nameTest", this.emptyNickUser.getName());
		assertEquals("nameTest", this.user.getName());
	}

	@Test
	public void testSetName() {
		this.emptyUser.setName("test");
		assertEquals("test", this.emptyUser.getName());
	}
	
	@Test
	public void testGetSurname() {
		assertEquals(null, this.emptyUser.getSurname());
		assertEquals("surnameTest", this.emptyNickUser.getSurname());
		assertEquals("surnameTest", this.user.getSurname());
	}

	@Test
	public void testSetSurname() {
		this.emptyUser.setSurname("test");
		assertEquals("test", this.emptyUser.getSurname());	
	}

	@Test
	public void testGetNick() {
		assertEquals(null, this.emptyUser.getNick());
		assertEquals(null, this.emptyNickUser.getNick());
		assertEquals("nickTest", this.user.getNick());
	}

	@Test
	public void testSetNick() {
		this.emptyUser.setNick("test");
		assertEquals("test", this.emptyUser.getNick());
	}

	@Test
	public void testGetPassword() {
		assertEquals(null, this.emptyUser.getPassword());
		assertEquals("passwordTest", this.emptyNickUser.getPassword());
		assertEquals("passwordTest", this.user.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		this.emptyUser.setPassword("test");
		assertEquals("test", this.emptyUser.getPassword());
	}
}
