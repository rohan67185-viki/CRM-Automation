package Opportunities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOpportunitiesWithLeadSource {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("http://localhost:8888/");

		String pid = driver.getWindowHandle();

		// Login
		WebElement un = driver.findElement(By.name("user_name"));
		WebElement pwd = driver.findElement(By.name("user_password"));

		un.sendKeys("admin");
		pwd.sendKeys("manager");

		driver.findElement(By.id("submitButton")).click();

// Create opportunities

		// Opportunities Name
		driver.findElement(By.cssSelector("a[href='index.php?module=Potentials&action=index']")).click();
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();

		String oppName = "DemoQspideruser";
		WebElement opportunField = driver.findElement(By.name("potentialname"));
		opportunField.sendKeys(oppName);

		// Related to
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif' and @tabindex]")).click();

		Set<String> ids = driver.getWindowHandles();
		for (String i : ids) {
			driver.switchTo().window(i);

			if (driver.getTitle().contains("Accounts&action")) {
				break;

			}
		}

		driver.findElement(By.id("3")).click();
//		String relatedName=driver.findElement(By.id("related_to_display")).getText();

		driver.switchTo().window(pid);

		// Lead Source Name
		WebElement leadSourceDD = driver.findElement(By.name("leadsource"));
		Select selDD = new Select(leadSourceDD);
		selDD.selectByValue("Existing Customer");
		String leadSourceName = selDD.getFirstSelectedOption().getText();

		// Expected Close date
		String date = "2025-11-02";
		WebElement dateField = driver.findElement(By.name("closingdate"));
		dateField.clear();
		dateField.sendKeys(date);

		// Sales Stage
		WebElement salesStageDD = driver.findElement(By.name("sales_stage"));
		Select selSalesDD = new Select(salesStageDD);
		selSalesDD.selectByValue("Value Proposition");
		String salesName = selSalesDD.getFirstSelectedOption().getText();

		Thread.sleep(8000);

		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		// verification

		// Opportunities Name
		String actualOppName = driver.findElement(By.id("dtlview_Opportunity Name")).getText();
		if (actualOppName.equals(oppName)) {
			System.out.println("Opp name Matched");
		} else {
			System.out.println("Opp name not Matched");
		}

		// LeadSource
		String actualLeadSource = driver.findElement(By.id("dtlview_Lead Source")).getText();
		if (actualLeadSource.equals(leadSourceName)) {
			System.out.println("Lead source name Matched");
		} else {
			System.out.println("Lead source name not Matched");
		}

		// Sales stage

		String actSalesName = driver.findElement(By.id("dtlview_Sales Stage")).getText();
		if (actSalesName.equals(salesName)) {
			System.out.println("Sales Matched");
		} else {
			System.out.println("Sales not Matched");
		}

		// LogOut
		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(profile).build().perform();
		driver.findElement(By.linkText("Sign Out")).click();

		Thread.sleep(3000);
		driver.quit();
	}

}

