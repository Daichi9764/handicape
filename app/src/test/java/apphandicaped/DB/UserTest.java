package apphandicaped.DB;


import static org.junit.Assert.*;
import apphandicaped.Database.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void testUserConstructorWithoutPassword() {
        User user = new User("CR", "7");
        assertEquals("CR", user.getFirstName());
        assertEquals("7", user.getLastName());
        assertEquals("", user.getUserPassword());
        assertNotNull(user.getUserCreateDate());
    }

    @Test
    public void testUserConstructorWithPassword() {
        User user = new User("CR", "7", "password123");
        assertEquals("CR", user.getFirstName());
        assertEquals("7", user.getLastName());
        assertEquals("password123", user.getUserPassword());
        assertNotNull(user.getUserCreateDate());
    }

    @Test
    public void testUserConstructorWithAllFields() {
        User user = new User("CR", "7", "password123", "cr.7@example.com", "Active");
        assertEquals("CR", user.getFirstName());
        assertEquals("7", user.getLastName());
        assertEquals("password123", user.getUserPassword());
        assertEquals("cr.7@example.com", user.getUserMail());
        assertEquals("Active", user.getUserStatus());
        assertNotNull(user.getUserCreateDate());
    }

    @Test
    public void testToString() {
        User user = new User("CR", "7", "password123", "cr.7@example.com", "Active");
        String expectedToString = "CR 7 null pswd : password123 ,cr.7@example.com, Active ";
        assertEquals(expectedToString, user.toString());
    }

    @Test
    public void testGetters() {
        User user = new User("CR", "7", "password123", "cr.7@example.com", "Active");
        assertEquals("CR", user.getFirstName());
        assertEquals("7", user.getLastName());
        assertEquals("password123", user.getUserPassword());
        assertEquals("cr.7@example.com", user.getUserMail());
        assertEquals("Active", user.getUserStatus());
        assertNotNull(user.getUserCreateDate());
    }
}

