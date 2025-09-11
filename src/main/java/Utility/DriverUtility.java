package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DriverUtility {
	WebDriver driver;
	Actions act;
		public DriverUtility(WebDriver driver){
			this.driver = driver;
			this.act =  new Actions(driver);
		}
	
	
	
	public void click(WebElement element) {
		act.click().build().perform();
	}

}
