package org.selenium.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.selenium.qa.baseclass.BaseClass;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {
	LoginPage loginpage;
	HomePage homepage;
	
	

	public HomePageTest(){
		super();
	}
	

	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginpage = new LoginPage();
		
		Thread.sleep(3000);
		homepage =  loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
    @Test(priority =0)
    public void verifyHomePageTitle() {
    	String homePageTitle = homepage.verifyHomePageTitle();
    	Assert.assertEquals(homePageTitle, "OrangeHRM","Home Page Title is Invalid");
    }
    
    @Test(priority =1)   
    public void verifyUserName() {
    	Assert.assertTrue(homepage.verifyPageUsername());
    }
    
    @Test(priority = 2)
    public void verifyAdminTab() throws InterruptedException {
    	Thread.sleep(3000);
    	homepage.clickOnAdmin();
    	Thread.sleep(3000);
    	homepage.addAdmin();
    	Thread.sleep(3000);
    	homepage.userRoleDropDown();
    	Thread.sleep(3000);
    	Actions keyDown = new Actions(driver);
    	keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();
    	Thread.sleep(3000);
    	homepage.enterEmpName("randomName");
    	Thread.sleep(5000);
    	Actions keyDown1 = new Actions(driver);
    	keyDown1.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
    	Actions keyDown11 = new Actions(driver);
    	keyDown11.sendKeys(Keys.chord(Keys.ENTER, Keys.ENTER)).perform();
    	Thread.sleep(3000);
    	homepage.status();
    	homepage.enterUsername("randomName");
    	homepage.enterpass("Pass");
    	homepage.confirmpass("Pass");
    	homepage.clickonSaveBtn();
    	
    }
	
    @Test(priority = 3)
    public void verifyPIM() throws InterruptedException {
    	homepage.clickonPim();
    	Thread.sleep(3000);
    	
    }
    
    @AfterMethod
    public void teraDown() {
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8000));
	driver.quit();
	}
		
}
		
	



