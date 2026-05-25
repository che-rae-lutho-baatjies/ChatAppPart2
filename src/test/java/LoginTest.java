
import com.mycompany.chatapppart2.Login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
import com.mycompany.chatapp.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions. *;


/**
 *
 * @author lutho
 */
public class LoginTest {
    
    Login login = new Login();

    //TEST USERNAME 
    
    @Test 
    public void testValidUsername() {
        assertTrue(login.checkUserName ("kyl_1"));
        
    }
   @Test 
   public void testInvalidUsername_NoLetters() {
       assertFalse(login.checkUserName("25!!!!!!"));
   }
    @Test 
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
   
    //TEST PASSWORD
    
    @Test 
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("CH&&sec@ke99!"));
       
    }
    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(login.checkPasswordComplexity("Passd"));
    }
    @Test
    public void testInvalidPassword_NoCapital() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    //TEST CELL PHONE NUMBER 
    
    @Test
    public void testValidPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    @Test
    public void testInvalidPhoneNumber_NoSAFormat() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
    
    //TEST REGISTRATION 
    
    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("kyl_1", "CH&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }
        @Test
        public void testFailedRegistration_InvalidPhoneNumber() {
            String result = login.registerUser("kyl_1", "CH&&sec@ke99!", "278966553");
            assertNotEquals("User registered successfully.", result);
        }
        
        //TEST LOGIN 
        
        @Test
        public void testSuccessfulLogin() {
            login.registerUser("kyl_1","CH&&sec@ke99!", "+27838968976");
            assertTrue(login.loginUser("kyl_1" , "CH&&sec@ke99!"));
        }
        @Test
        public void testFailedLogin() {
            login.registerUser("kyl_1", "CH&&sec@ke99!", "+27838968976");
            assertFalse(login.loginUser("WrongUsername", "kyle!!!!!!"));
           
        }
        @Test
        public void testLoginStatusMessage_Success() {
            String message = login.returnLoginStatus(true);
            assertTrue(message.contains("Welcome"));
        }
        @Test 
        public void testLoginStatusMessage_Failure() {
            String message = login.returnLoginStatus(false);
            assertFalse(message.contains("Failed"));
        }
}    

    

