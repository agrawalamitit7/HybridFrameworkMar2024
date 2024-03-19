package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends Base{
	
	public WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage=homePage.selectLoginOption();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.xpath("//a[contains(@href,'account/login')]")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredential() {
		
	    //LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Change your password link is not available");
		
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		//Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed(),"Change your password link is not available");
		
		
	}
	@Test(priority=2,dataProvider="supplyLoginTestData")
	public void verifyLoginWithValidCredentialExcel(String email,String password) {
		
		//LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Change your password link is not available");
		
		//driver.findElement(By.id("input-email")).sendKeys(email);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		//Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed(),"Change your password link is not available");
		
		
	}
	
	@DataProvider
	public Object[][] supplyLoginTestData(){
		
		Object[][] data= Utilities.getTestDataFromExcel("Login");
			
		/*
		 * {{"amit@yopmail.com","amit"}, {"amit@yopmail.com","amit"},
		 * {"amit@yopmail.com","amit"} };
		 */
		
		return data;
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidCredential() {

		//LoginPage loginPage=new LoginPage(driver);
		
		loginPage.enterEmailAddress(Utilities.generateEmailIdWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailIdWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		
		String WarningMessageTextActual=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		//String WarningMessageTextActual=driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
		String WarningMessageTextExpected=dataProp.getProperty("warningMessageEmailPasswordNotMatch");
		Assert.assertTrue(WarningMessageTextActual.contains(WarningMessageTextExpected),"Warning message is not correct");
		
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		//LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailIdWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailIdWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		
		String WarningMessageTextActual=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		//String WarningMessageTextActual=driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
		String WarningMessageTextExpected=dataProp.getProperty("warningMessageEmailPasswordNotMatch");
		Assert.assertTrue(WarningMessageTextActual.contains(WarningMessageTextExpected),"Warning message is not correct");
		
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
       
		//LoginPage loginPage=new LoginPage(driver);
		
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		//loginPage.enterPassword("amit");//valid password
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		
		String WarningMessageTextActual=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		//String WarningMessageTextActual=driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
		String WarningMessageTextExpected=dataProp.getProperty("warningMessageEmailPasswordNotMatch");
		Assert.assertTrue(WarningMessageTextActual.contains(WarningMessageTextExpected),"Warning message is not correct");
		
		
	}
	
	@Test(priority=6,dependsOnMethods= {"verifyLoginWithValidEmailAndInvalidPassword"})
	public void verifyLoginWithoutProvidingCredential() {

		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
		//LoginPage loginPage=new LoginPage(driver);
		loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//*[@value='Login']")).click();
		
		String WarningMessageTextActual=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		//String WarningMessageTextActual=driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
		String WarningMessageTextExpected=dataProp.getProperty("warningMessageEmailPasswordNotMatch");
		Assert.assertTrue(WarningMessageTextActual.contains(WarningMessageTextExpected),"Warning message is not correct");
		
		
	}
	
	/*
	 * public String generateTimeStamp() { Date date=new Date(); return
	 * date.toString().replace(" ", "_").replace(":", "_"); }
	 */

}
