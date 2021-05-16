package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PagesBase {
	
	public WebDriver driver;
    public WebDriverWait wait;    
    
  //Constructor
    public PagesBase (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    //Get Element
    public WebElement getElement(By elementLocation){
        return driver.findElement(elementLocation);
    }
    
    //Get List Of Elements
    public List<WebElement> getElements(By elementLocation){
        return driver.findElements(elementLocation);
    }
    
 	//Click Method
    public void click (By elementLocation) {
        driver.findElement(elementLocation).click();
    }  
    
    //Write Text
    public void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }    
    
    //Read Text
    public String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }
}