package selenium_practice;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlingDemo {

	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		
		String parentWindow=driver.getWindowHandle();
		
		driver.findElement(By.linkText("Open a popup window")).click();//first child window
		driver.findElement(By.linkText("Blogger")).click();//second child window
		
		Set<String> allChildWindow = driver.getWindowHandles();
		
		Iterator<String> itr1 = allChildWindow.iterator();
		
		while(itr1.hasNext()) {
			String singleChildWindow=itr1.next();
			driver.switchTo().window(singleChildWindow);
			
			if(driver.getTitle().equals("New Window")) {
				String text=driver.findElement(By.cssSelector("h3")).getText();
				System.out.println(text);
				driver.close();
				break;
			}
		}
		
         Iterator<String> itr2 = allChildWindow.iterator();
		
		 while(itr2.hasNext()) {
			String singleChildWindow=itr2.next();
			driver.switchTo().window(singleChildWindow);
			
			if(driver.getTitle().equals("Blogger.com - Create a unique and beautiful blog easily.")) {
				
				String text=driver.findElement(By.cssSelector(".hero--header p")).getText();
				System.out.println(text);
				break;
			}
		}
		
		 driver.switchTo().window(parentWindow);
		 //or
		 //driver.switchTo().defaultContent(); - not working
		 
		 driver.findElement(By.cssSelector("#ta1")).sendKeys("Amit A");

	}

}
