package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PagesBase {
	
	public SearchPage(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);
    }
	
	//Instance for the log4j2 logger is created
    Logger loggerSearchPg = LogManager.getLogger(SearchPage.class);
	
    //Path for the page 2 button is given
	public String page2Loc = ".//*[@class='clearfix']/li[2]/a[1]"; //XPath
	
	//Go to the specified page on the searched item page by button location
	public void goToPage (String pageBtnPath) throws InterruptedException{
		Actions builder = new Actions(driver);	
		//Page 2 button is unclickable at the initial position therefore the cursor is moved beneath the button for better positioning
		WebElement clickElement= driver.findElement(By.xpath(".//*[@class='related-searches-container hidden-m clearfix']/div[1]"));
		builder.moveToElement(clickElement).perform();  
		loggerSearchPg.trace("Cursor has been moved to an element beneath page 2 button");
		//Page 2 button is clicked
    	click(By.xpath(pageBtnPath));
    	loggerSearchPg.trace("Second Page button is clicked");
    	loggerSearchPg.info("Second Page is opened");		
    	
	}
}
