package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends PagesBase{
	
	public CartPage(WebDriver driver, WebDriverWait wait) {
    	super(driver, wait);
    }
	
	//Instance for the log4j2 logger is created
    Logger loggerCartPg = LogManager.getLogger(CartPage.class);
	
    //Location of the item price text element
	public String cartPriceLoc ="new-price"; // className
	
	//Dropdown number of item selection path
	public String drpDownSlctLoc ="//select[@class='amount']"; //xpath 
	
	//Delete items button path 
	public String dltItemsLoc = "//body/div[@id='main-content']/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[6]/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/a[1]/i[1]"; //xpath
	
	//the variable to store the price of the product is defined
	public String priceText2 = "";
	
	//get the price of the item on the cart page
	public void getCartPgPrice(String price2Location){
		WebElement priceAtCart= driver.findElement(By.className(price2Location));
	    priceText2 = priceAtCart.getText();
	    loggerCartPg.info("Item price on the Check-out Page: " + priceText2);
	}
	
	//check if the price on the showcase matches the price on the cart page
	public void checkPrices(String price1, String price2) {
	    if(price1.equals(price2)){
	    	loggerCartPg.info("The price on the Product page is equal to the price on the Check-out Page");
	    }
	    else {
	    	loggerCartPg.warn("The price on the Product page is not equal to the price on the Check-out Page");
		}
	}
	
	//change the number of items in the cart
	public void changeNumberOfItem(String number){
	    Select drpItemNumber = new Select(driver.findElement(By.xpath(drpDownSlctLoc)));
	    drpItemNumber.selectByValue(number);
	    loggerCartPg.info("Number of items in the cart has been changed to " + number);
	}
	
	//check the number of items in the cart from select path
	public String getNumberOfItem(String selectPath) {
		Select drpItemNumber = new Select(driver.findElement(By.xpath(selectPath)));
		WebElement itemNumber = drpItemNumber.getFirstSelectedOption();
		return itemNumber.getText();
	}
	
	//Empty the cart
	public void emptyCart() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.findElement(By.xpath(dltItemsLoc)).click();
	    loggerCartPg.info("The items in the cart is deleted and the cart is empty");
	}
		
	
	
}
