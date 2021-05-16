package tests;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;
import pages.SearchPage2;

import static org.junit.Assert.*;


public class TestCase {
	
	//Chrome driver.exe path is stored in ChromePath Variable
	protected static String ChromePath = "C:\\Users\\user\\chromedriver_win32\\chromedriver.exe";
	//Service allowing us to reach the chrome driver
	protected static ChromeDriverService service;
	//driver instance
	protected static WebDriver driver;
	//Explicit wait to prevent execution before another execution is completed
	protected static WebDriverWait wait;
	
	//Instance for the log4j2 logger is created
    Logger loggerTestCase = LogManager.getLogger(TestCase.class);
	
	//setting up the initial conditions
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new ChromeDriverService.Builder().
                usingDriverExecutable(new File(ChromePath)).
                usingAnyFreePort().
                build();
         try {
        	 service.start();
         } 
         catch (Exception e) {
        	 e.printStackTrace();
         }
         
         System.setProperty("webdriver.chrome.driver", ChromePath);
         
         driver = new  RemoteWebDriver(service.getUrl(),DesiredCapabilities.chrome());
         
         wait = new WebDriverWait(driver,15);
         
         driver.manage().window().maximize();
	}
	
	//Ending and quitting the driver and service after the tests
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
		service.stop();
	}
	
	//Implementing the tests
	@Test
	public void test() throws InterruptedException{
		
		//Create instances for home page, home test and login page
		HomePage home = new HomePage(driver, wait);
		LoginPage login = new LoginPage(driver, wait);
		
		//check if the current url is the home page
		loggerTestCase.info("Confirming the home page...");
        assertEquals(home.checkMenuLinks(home.btnMainLogo), home.webSite);      
        loggerTestCase.info("Home page is confirmed.");
		
		//hover to the login section and click login on the hover down menu
		home.hoverLogin(home.hoverMain, home.hoverSub);
		
		//Enter user-name & password and log in 
		login.verifyUsernameAndPassword(login.loginMail, login.loginPassword);
		
		//Search for the specified item, which is Bilgisayar(Computer) for this case
		home.searchForItem(home.searchField, "Bilgisayar", home.searchButton);
		
		//Instance of the search page is created
		SearchPage srcPage = new SearchPage(driver, wait);
		//Click on the second page button on the search page
		srcPage.goToPage(srcPage.page2Loc);
		//Testing if the current page is second page
		loggerTestCase.info("Confirming Second Search Page...");
		assertEquals("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2", driver.getCurrentUrl());		
		loggerTestCase.info("Second Search Page is Confirmed.");		
		
		//Instance of the Search Page 2 is created
		SearchPage2 srcPage2 = new SearchPage2(driver, wait);
		//Random product is opened
		srcPage2.go2RandomItem(srcPage2.randomProductLoc);
		 
		//Instance of the product page is created
		ProductPage prdcPage = new ProductPage(driver, wait);
		//Price on the view page is recorded
		prdcPage.getViewPgPrice(prdcPage.priceLoc);	     
	    //Adding to the cart
	    prdcPage.add2Basket(prdcPage.move2Add2Cart, prdcPage.addToCartBtnLoc);
	    //Going to the cart page
	    prdcPage.go2Cart((prdcPage.go2CartBtnLoc)); 
	    	     
	    //Instance of the cart page is created
	    CartPage cartPage = new CartPage(driver, wait);
	    //Price of the product in the cart is read
	    cartPage.getCartPgPrice(cartPage.cartPriceLoc);	     
	    //Prices are compared with each other
	    cartPage.checkPrices(prdcPage.priceText,cartPage.priceText2);    
	     
	    //Increasing the number of elements in the cart to 2
	    cartPage.changeNumberOfItem("2");
	    //thread is made to sleep to until the number of item is 2
	    Thread.sleep(100);
	     
	    //getting the current number of items in the cart
	    String currNumberOfItem = cartPage.getNumberOfItem(cartPage.drpDownSlctLoc);
	    
	    //confirm that the number of items in the cart is 2
	    loggerTestCase.info("Confirming that the number of Items in the cart is 2...");
	    assertEquals("2", currNumberOfItem);
	    loggerTestCase.info("The number of Items in the cart is confirmed to be " + currNumberOfItem +".");
	     
	    //Emptying the cart
	    cartPage.emptyCart();
	    loggerTestCase.info("!!! ALL TESTS ARE SUCCESSFULLY COMPLETED !!!");
	    Thread.sleep(200);
	
	}
	
}
