package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage2 extends PagesBase {
	
	public SearchPage2(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);
    }
	
	//Instance for the log4j2 logger is created
    Logger loggerSearchPg2 = LogManager.getLogger(SearchPage2.class);
	
	//Path to the random product
	public String randomProductLoc = ".//*[@class='catalog-view clearfix products-container']/li[2]/a[1]"; //xpath
	
	public void go2RandomItem(String itemPath) {
		driver.findElement(By.xpath(itemPath)).click();
		loggerSearchPg2.info("A random item is clicked and opened");
	}
	
}
