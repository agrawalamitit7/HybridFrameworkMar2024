package com.tutorialninja.qa.testcases;

//updated comment by team member - with more details

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.pages.RegistrationSuccessPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	 RegisterPage registerPage;
	 RegistrationSuccessPage registrationSuccessPage;
	 
	 public RegisterTest() {
		 super();
	 }
	 
	 @BeforeMethod
	 public void setUp() {
		    driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		    HomePage homePage=new HomePage(driver);
		    homePage.clickOnMyAccount();
		    registerPage= homePage.selectRegisterOption();
			//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		    //driver.findElement(By.linkText("Register")).click();
	 }
	 
	 @AfterMethod
	 public void tearDown() {
		 driver.quit();
	 }
	 
	@Test(priority=1) 
	public void verifyRegisterAnAccountWithMandatoryField() {

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.id("input-confirm")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		
//		String actualSuccessMessageAfterRegistration=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
//		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("successfulMessageAfterRegistration")), "Registration not done successfully");
		
		//RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailIdWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registrationSuccessPage=registerPage.clickContinueButton();
		
		//RegistrationSuccessPage registrationSuccessPage=new RegistrationSuccessPage(driver);
		
		String actualSuccessMessageAfterRegistration=registrationSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("accountSuccessfullyCreatedMessage")), "Registration not done successfully");
	}
	
	@Test(priority=2) 
	public void verifyRegisterAnAccountWithAllField() {
		

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.id("input-confirm")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		
//		String actualSuccessMessageAfterRegistration=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
//		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("successfulMessageAfterRegistration")), "Registration not done successfully");
		
		//RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailIdWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registrationSuccessPage=registerPage.clickContinueButton();
		//RegistrationSuccessPage registrationSuccessPage=new RegistrationSuccessPage(driver);
		String actualSuccessMessageAfterRegistration=registrationSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("accountSuccessfullyCreatedMessage")), "Registration not done successfully");
		
	}
	
	@Test(priority=3) 
	public void verifyRegisterAnAccountWithExistingEmailId() {
		
//     	driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys("amit@yopmail.com");
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.id("input-confirm")).sendKeys(dataProp.getProperty("password"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		
//		String actualMessageForExistingEmail=driver.findElement(By.cssSelector(".alert-dismissible")).getText();
//		System.out.println(actualMessageForExistingEmail);
//		Assert.assertTrue(actualMessageForExistingEmail.contains(dataProp.getProperty("duplicteEmailWarningMessageWhileRegistration")), "Use different email id for registration");
		
		//RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickContinueButton();
		String actualMessageForExistingEmail=registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualMessageForExistingEmail.contains(dataProp.getProperty("warningEmailAlreadyRegistered")), "Use different email id for registration");
	}
	
	@Test(priority=4) 
	public void verifyRegisterAnAccountWithoutGivingAnyDetails() {
		
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		
//		String actualWarningMessageForNotSelectingPrivacyPolicy=driver.findElement(By.cssSelector(".alert-dismissible")).getText();
//		
//		System.out.println(actualWarningMessageForNotSelectingPrivacyPolicy);
//		Assert.assertTrue(actualWarningMessageForNotSelectingPrivacyPolicy.contains(dataProp.getProperty("privacyPolicyWarningMessage")), "Privacy Policy is not selected");
//		
//		String actualFirstNameNotFillingMessage=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
//		Assert.assertTrue(actualFirstNameNotFillingMessage.contains(dataProp.getProperty("firstNameNotEnteredErrorMessage")), "First name should not be empty");
		
		//RegisterPage registerPage=new RegisterPage(driver);
		registerPage.clickContinueButton();
		String actualWarningMessageForNotSelectingPrivacyPolicy=registerPage.retrievePrivacyPolicywarning();
		Assert.assertTrue(actualWarningMessageForNotSelectingPrivacyPolicy.contains(dataProp.getProperty("warningPrivaryPolicy")), "Privacy Policy is not selected");
		String actualFirstNameNotFillingMessage=registerPage.retrieveFirstNameNotEnteredWarning();
		Assert.assertTrue(actualFirstNameNotFillingMessage.contains(dataProp.getProperty("firstNameMissingMessage")), "First name should not be empty");
	
	}


}
