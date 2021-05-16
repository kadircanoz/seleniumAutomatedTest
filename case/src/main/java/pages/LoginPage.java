package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PagesBase{
	
	// Constructor
    public LoginPage(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);
    	
    }     
    
    //Instance for the log4j2 logger is created
    Logger loggerLoginPg = LogManager.getLogger(LoginPage.class);
    
    //WebElements
    public String txtMail = "L-UserNameField"; //   id
    public String txtPassword = "L-PasswordField"; //   id
    public String btnNext = "gg-login-enter"; //   id     
    
    //PUT VALÝD E-MAÝL AND PASSWORD BELOW
    public String loginMail = "email@email.com";
    public String loginPassword = "password";
    
    public boolean verifyUsernameAndPassword(String email, String password) throws InterruptedException {     
    	     
    	// Write Email to textbox
       	writeText(By.id(txtMail), email);
       	loggerLoginPg.trace("Email is written into the e mail textbox");
    		
    	// Write password
   		writeText(By.id(txtPassword),password);
   		loggerLoginPg.trace("Password is written into the password textbox");
    		
   		// Click Next
       	click(By.id(btnNext)); 
       	loggerLoginPg.info("Login Succesful");
        	
   		// Sleep a little bit for page load
       	Thread.sleep(600);           
    	// return does last url have signinoptions
   		return driver.getCurrentUrl().contains("signinoptions");
   		
    	
    }
	
}
