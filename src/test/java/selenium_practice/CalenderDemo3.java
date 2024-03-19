package selenium_practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalenderDemo3 {
	
	static WebDriver driver=null;
	public static void main(String[] args) {

    driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.path2usa.com/travel-companion/");
    
    
    JavascriptExecutor js= ((JavascriptExecutor)driver);
    WebElement element=driver.findElement(By.xpath("//*[contains(@class,'elementor-field-type-date elementor-field-group')]/label"));
    js.executeScript("arguments[0].scrollIntoView(true);", element);
    WebDriverWait wd=new WebDriverWait(driver,Duration.ofSeconds(10));
    wd.until(ExpectedConditions.elementToBeClickable(element));
    js.executeScript("arguments[0].click();", element);
    
    //element.click();
    
   // Actions actions = new Actions(driver); 
    //actions.moveToElement(element).perform();
    //actions.click().perform();
    
  
    
     WebDriverWait wd1=new WebDriverWait(driver,Duration.ofSeconds(10));
     wd1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flatpickr-calendar')]")));

     selectDateInCalender("7","July","2025");
	}   
	
	  public static void selectDateInCalender(String selectDay,String selectMonth,String selectYear) {
	  
		  
		 // System.out.println(driver.findElement(By.className("flatpickr-month")).getText());
		  
		  String year=driver.findElement(By.xpath("//input[contains(@class,'cur-year')]")).getDomProperty("min");
		  System.out.println(year);
	  
	  String monthOfYear =driver.findElement(By.className("flatpickr-current-month")).getText().trim();
	  System.out.println(monthOfYear);
	  

	  //String[] month_year=monthYear.split(" "); 
	  //String month=month_year[0]; 
	  //String year=month_year[1];
	  
	  while(!(monthOfYear.equals(selectMonth) && year.equals(selectYear))) {
	  driver.findElement(By.xpath("//span[@class='flatpickr-next-month']")).click(); 
	  monthOfYear = driver.findElement(By.className("flatpickr-current-month")).getText().trim();
	  year=driver.findElement(By.xpath("//input[contains(@class,'cur-year')]")).getDomProperty("min");
	  
	  }
	  
	  driver.findElement(By.xpath("//div[@class='flatpickr-days']//span[text()='" + selectDay + "']")).click(); 
	  
	  //div[@class='flatpickr-days']//span[text()='25']
	  }
	 

}
