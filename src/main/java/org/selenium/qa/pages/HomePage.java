package org.selenium.qa.pages;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.checkerframework.framework.qual.FieldInvariant;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends org.selenium.qa.baseclass.BaseClass {
	
	public HomePage() 
    {
	 PageFactory.initElements(driver,this);	
    }
	
	@FindBy(xpath="//h6[normalize-space()='Dashboard']")
	WebElement dashboard;
	
	@FindBy(xpath= "//li[1]//a[1]//span[1]")
	WebElement admin;
	
    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addadmin;
    
    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--focus']")
	WebElement clickOnAdminRole;
	
    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement userroledropdown;
    
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement empname;
    
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement clickonstatus;
    
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement username;
    
    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement adminpass;
    
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPass;
    
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement savebtn;
    
    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pim;
    
    public String  verifyHomePageTitle() {
    	return driver.getTitle();
    }
	
    public boolean verifyPageUsername() {
    	return dashboard.isDisplayed();
    }
    
   public String clickOnAdmin() {
	   admin.click();
	return null;
	   
   }
    public String addAdmin() {
    	addadmin.click();
		return null;
    	
    }
   
   public void userRoleDropDown() {
	   userroledropdown.click();
   }
	public void selectAdminRoleDropDown() {
		Select drop = new Select(userroledropdown);
		drop.selectByIndex(1);
	}
	
    public void selectAdminRole() {
    	userroledropdown.click();
    }
    
    public String enterEmpName(String name) {
    	
    	empname.sendKeys("Test");
    	
		return name;
    	
    	}
    
    public void status() {
    	clickonstatus.click();
    	Actions keyDown = new Actions(driver);
    	keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();
    	
    }
    
    public String enterUsername(String name) {
    	
    	String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	int nameLength = 8;
        // Initialize a StringBuilder to build the random name
        StringBuilder randomName = new StringBuilder();

        // Create a Random object
        Random random = new Random();

        // Generate random characters and append them to the StringBuilder
        for (int i = 0; i < nameLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            randomName.append(allowedChars.charAt(randomIndex));
        }
        username.sendKeys(randomName);
        return randomName.toString();
    }
    
    public String enterpass(String Pass) {
    	
	adminpass.sendKeys("Test@123");
	return Pass;
    	
		
    	
    	}
    
    public String confirmpass(String Pass) {
    	
    	confirmPass.sendKeys("Test@123");
    	return Pass;
        	
    		
        	
        	}
    public void clickonSaveBtn() {
    	
    	savebtn.click();
    }
    
    public void clickonPim() {
    	pim.click();
    	
    }
}
