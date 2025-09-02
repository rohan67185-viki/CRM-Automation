package Opportunities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

https://github.com/rohan67185-viki/CRM-Automation.githttps://github.com/rohan67185-viki/CRM-Automation.git
	public class CreateOpp {

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

			String oppName = "JspiderCrm1200";
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
//			

			driver.switchTo().window(pid);

			
			WebElement relatedField = driver.findElement(By.name("related_to_display")); // visible input box

			String relatedTo=relatedField.getAttribute("value");

			

			// Expected Close date
			String date = "2026-10-06";
			WebElement dateField = driver.findElement(By.name("closingdate"));
			dateField.clear();
			dateField.sendKeys(date);
//			System.out.println("Value: " + dateField.getAttribute("value"));
			String closeDate=dateField.getAttribute("value");
			System.out.println("Close date is :- "+closeDate);
			

			// Sales Stage
			WebElement salesStageDD = driver.findElement(By.name("sales_stage"));
			Select selDD = new Select(salesStageDD);
			selDD.selectByValue("Id. Decision Makers");
			String salesName = selDD.getFirstSelectedOption().getText();

//			Thread.sleep(8000);

			driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

	// verification

			// Opportunities Name
			String actualOppName = driver.findElement(By.id("dtlview_Opportunity Name")).getText();
			if (actualOppName.equals(oppName)) {
				System.out.println("Opp name Matched");
			} else {
				System.out.println("Opp name not Matched");
			}

			// Related name
			String actRelated=driver.findElement(By.cssSelector("a[title='Organizations']")).getText();
			if (actRelated.equals(relatedTo)) {
				System.out.println("related name Matched");
			} else {
				System.out.println("related name not Matched");
			}

			// Expected Close date

			String actualDate=driver.findElement(By.xpath("//td[text()='Expected Close Date']/following-sibling::td")).getText();
			System.out.println("Actual Date is :-"+actualDate);
			if (actualDate.equals(closeDate)) {
				System.out.println("Date Matched");
			} else {
				System.out.println("Date not Matched");
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



