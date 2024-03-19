package selenium_practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalenderDemo1 {
 
	static WebDriver driver=null;
	public static void main(String[] args) {

    driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://seleniumpractise.blogspot.com/2016/08/");
    driver.findElement(By.id("datepicker")).click();
    
    WebDriverWait wd=new WebDriverWait(driver,Duration.ofSeconds(10));
    wd.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));

    selectDateInCalender("7","July","2025");
    
  }
	
	public static void selectDateInCalender(String selectDay,String selectMonth,String selectYear) {
		
	    
	    String monthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
	    System.out.println(monthYear);
	    
	    String[] month_year=monthYear.split(" ");
	    String month=month_year[0];
	    String year=month_year[1];
	    
	    while(!(month.equals(selectMonth) && year.equals(selectYear))) {
	    driver.findElement(By.xpath("//span[text()='Next']")).click();
	    monthYear = driver.findElement(By.className("ui-datepicker-title")).getText();	
	    month=monthYear.split(" ")[0];
	    year=monthYear.split(" ")[1];	
	    }
	    
	    driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='" + selectDay + "']")).click();
		
	}

}
