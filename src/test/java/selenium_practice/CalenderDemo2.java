package selenium_practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//using JS Executor
public class CalenderDemo2 {
	

	public static void main(String[] args) {

	WebDriver driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
    
    JavascriptExecutor js= ((JavascriptExecutor)driver);
    js.executeScript("document.getElementById('datepicker').value='04/11/2024'");
    


}
}