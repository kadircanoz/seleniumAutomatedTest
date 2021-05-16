package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PagesBase{
	//Instance of the  log4j2 logger is created
	Logger loggerHomePg = LogManager.getLogger(HomePage.class);
	
	//Global variable created to store the main web page
	public String webSite = "https://www.gittigidiyor.com/";
	
	// Constructor
	public HomePage(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);    	
    	driver.get(webSite);
    	loggerHomePg.info(webSite + " is opened");
    }		
    
    //Web Elements of the home page
    public String btnMainLogo = "//header/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]";   //   Xpath 
    public String hoverMain = "//header/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]";
    public String hoverSub = "//header/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]";
    public String searchField = "//header/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]";
    public String searchButton = "//header/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/button[1]";
    
    //Login hover menu click for home page
    public void hoverLogin (String mainMenuLoc, String subMenuLoc) throws InterruptedException {
    	Actions builder = new Actions(driver);
    	//Move cursor over the Login Hover Menu
    	builder.moveToElement(driver.findElement(By.xpath(mainMenuLoc))).perform();
    	loggerHomePg.trace("Cursor has been moved to the hover login element");
    	//Wait for the hover menu to open
    	Thread.sleep(1000);
    	//Click the Login Button
    	WebElement clickElement= driver.findElement(By.xpath(subMenuLoc));
    	builder.moveToElement(clickElement).click().perform();
    	loggerHomePg.info("Login button has been clicked and the login page is opened");
    }
    
    //Search method to search for the specified item
    public void searchForItem(String searchLoc, String Item, String srcBtnLoc) throws InterruptedException {
    	writeText(By.xpath(searchLoc),Item);
    	click(By.xpath(srcBtnLoc));
    	Thread.sleep(500);
    	loggerHomePg.info(Item + " has been searched");
    }
    
    //method to return the link for the assertion method of the home page
    public String checkMenuLinks(String button){
        click(By.xpath(button));
        return driver.getCurrentUrl();
    }
}
