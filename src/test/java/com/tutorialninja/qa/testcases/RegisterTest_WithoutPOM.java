package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest_WithoutPOM extends Base{

	WebDriver driver;
	
	public RegisterTest_WithoutPOM() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[contains(@href,'account/register')]")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountWithMandatoryField() {

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailIdWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualSuccessMessageAfterRegistration = driver.findElement(By.xpath("//div[@id='content']/h1"))
				.getText();
		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("accountSuccessfullyCreatedMessage")),
				"Registration not done successfully");

	}

	@Test(priority = 2)
	public void verifyRegisterAnAccountWithAllField() {

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailIdWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualSuccessMessageAfterRegistration = driver.findElement(By.xpath("//div[@id='content']/h1"))
				.getText();
		Assert.assertTrue(actualSuccessMessageAfterRegistration.contains(dataProp.getProperty("accountSuccessfullyCreatedMessage")),
				"Registration not done successfully");

	}

	@Test(priority = 3)
	public void verifyRegisterAnAccountWithExistingEmailId() {

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("amit@yopmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualMessageForExistingEmail = driver.findElement(By.cssSelector(".alert-dismissible")).getText();
		System.out.println(actualMessageForExistingEmail);
		Assert.assertTrue(actualMessageForExistingEmail.contains(dataProp.getProperty("warningEmailAlreadyRegistered")),
				"Use different email id for registration");
	}

	@Test(priority = 4)
	public void verifyRegisterAnAccountWithoutGivingAnyDetails() {

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualWarningMessageForNotSelectingPrivacyPolicy = driver
				.findElement(By.cssSelector(".alert-dismissible")).getText();

		System.out.println(actualWarningMessageForNotSelectingPrivacyPolicy);
		Assert.assertTrue(actualWarningMessageForNotSelectingPrivacyPolicy
				.contains(dataProp.getProperty("warningPrivaryPolicy")), "Privacy Policy is not selected");

		String actualFirstNameNotFillingMessage = driver
				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstNameNotFillingMessage.contains(dataProp.getProperty("firstNameMissingMessage")),
				"First name should not be empty");

	}

}