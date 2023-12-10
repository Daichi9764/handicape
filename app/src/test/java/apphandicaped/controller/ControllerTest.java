package apphandicaped.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() throws SQLException {
        controller = Controller.getInstance();
    }

    @After
    public void tearDown() throws IOException {
        if (controller != null) {
            controller.close();
        }
    }

    @Test
    public void testCheckTokensNotExist() throws SQLException {
        char[] password = "password123".toCharArray();
        Controller.loginResult result = controller.CheckTokens("Nonexistent", "User", password);
        assertEquals(Controller.loginResult.NotExist, result);
    }

    @Test
    public void testCheckTokensBadPassword() throws SQLException {
        char[] password = "wrongpassword".toCharArray();
        Controller.loginResult result = controller.CheckTokens("cr", "7", password);
        assertEquals(Controller.loginResult.BadPassword, result);
    }

    @Test
    public void testCheckTokensConnected() throws SQLException {
        char[] password = "password123".toCharArray();
        Controller.loginResult result = controller.CheckTokens("cr", "7", password);
        assertEquals(Controller.loginResult.Connected, result);
        assertNotNull(controller.connectedUser);
    }

    @Test
    public void testRegisterAlreadyExist() throws SQLException {
        char[] password = "password123".toCharArray();
        char[] checkPassword = "password123".toCharArray();
        Controller.RegisterResult result = controller.Register("cr", "7", password, checkPassword, "cr.7@example.com",
                "Active");
        assertEquals(Controller.RegisterResult.AlreadyExist, result);
    }

    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Test
    public void testRegisterRegistered() throws SQLException {
        char[] password = "newpassword".toCharArray();
        char[] checkPassword = "newpassword".toCharArray();
        String randomFirstName = generateString(new Random(), "abcdefghijklmnopqrstuvwxyz", 10);
        String randomLastName = generateString(new Random(), "abcdefghijklmnopqrstuvwxyz", 10);
        Controller.RegisterResult result = controller.Register(randomFirstName, randomLastName, password, checkPassword,
                "new.user@example.com", "Active");
        assertEquals(Controller.RegisterResult.Registered, result);
    }

    @Test
    public void testRegisterWrongPassword() throws SQLException {
        char[] password = "password123".toCharArray();
        char[] checkPassword = "wrongpassword".toCharArray();
        String randomFirstName = generateString(new Random(), "abcdefghijklmnopqrstuvwxyz", 10);
        String randomLastName = generateString(new Random(), "abcdefghijklmnopqrstuvwxyz", 10);
        Controller.RegisterResult result = controller.Register(randomFirstName, randomLastName, password, checkPassword, "cr.7@example.com",
                "Active");
        assertEquals(Controller.RegisterResult.wrongpassword, result);
    }
}
