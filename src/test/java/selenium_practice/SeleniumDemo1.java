package selenium_practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDemo1 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		
		List<WebElement> linkOptions=driver.findElements(By.xpath("//div[@id='LinkList1']//a"));
		
		for(WebElement link:linkOptions) {
			link.click();
			Thread.sleep(2000);
			driver.navigate().back();
		}

	}

}
