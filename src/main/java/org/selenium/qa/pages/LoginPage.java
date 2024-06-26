package org.selenium.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends org.selenium.qa.baseclass.BaseClass { 
	
    //pageFatcory
	
	@FindBy(name ="username") 
	WebElement username; 
	
	@FindBy(name ="password") 
	WebElement password;
	
    @FindBy(xpath="//button[contains(@class,'oxd-button oxd-button--medium')]")
    WebElement loginButton;
    
    @FindBy(xpath="//a[contains(@href,'password')]")
    WebElement forgotPassword; 
    
    By button = By.xpath("//button[contains(@class,'oxd-button oxd-button--medium')]");
    
    
     // Initializing the Page Object
     public LoginPage() 
     {
 	 PageFactory.initElements(driver,this);	
     }
     
     //Actions
     
     public String validateLoginPageTitle() {
    	 return driver.getTitle();
     }
     
	 public HomePage login(String uname, String pass) {
		
		username.sendKeys(uname);
		password.sendKeys(pass);
      
		waitForElementToAppear(button);
		
		loginButton.click();
		
		return new HomePage();	
	}
	
}



	
