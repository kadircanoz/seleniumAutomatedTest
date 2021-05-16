package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PagesBase {
	
	public ProductPage(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);
    }
	
	//Instance for the log4j2 logger is created
    Logger loggerProductPg = LogManager.getLogger(ProductPage.class);
	
    //The Locations to be used on the page are stored in global variables
	public String priceLoc = ".//*[@id='sp-price-lowPrice']"; //xpath
	public String move2Add2Cart = "watch-share-report"; //id
	public String addToCartBtnLoc = "add-to-basket"; // id
	public String go2CartBtnLoc = "dIB"; // className
	
	//The variable to store the price on the product page is defined
	public String priceText = "";
	
	//Add to basket by first moving to an element right under the add to basket button and then clicking the button 
	//because the add button is inaccessible at the initial page position
	public void add2Basket (String move2Loc, String addBtnLoc) throws InterruptedException {
    	Actions builder = new Actions(driver);
    	//The cursor is moved to an element beneath the add button 
    	builder.moveToElement(driver.findElement(By.id(move2Loc))).perform();
    	loggerProductPg.trace("Cursor has been moved to an element beneath the basket button");
    	Thread.sleep(1000);
    	
    	//clicking the add to basket button
    	WebElement clickElement= driver.findElement(By.id(addBtnLoc));
    	builder.moveToElement(clickElement).click().perform();
    	
    	loggerProductPg.info("1 Item has been added to cart");
    	driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    	
	}
	
	//Move to the cart Page
	public void go2Cart (String cartBtnLoc) throws InterruptedException {
    	 driver.findElement(By.className(cartBtnLoc)).click();
    	 loggerProductPg.info("Go to cart button is clicked and Cart Page is opened");
    }
	
	//get price of the view page
	public void getViewPgPrice(String priceLocation){
		WebElement priceAtCart= driver.findElement(By.xpath(priceLocation));
	    priceText = priceAtCart.getText();
	    loggerProductPg.info("Item price on the product page: " + priceText);
	}
	
	
}
