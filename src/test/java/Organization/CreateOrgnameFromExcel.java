package Organization;
	

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.Properties;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;

	public class CreateOrgnameFromExcel {

		public static void main(String[] args) throws IOException, InterruptedException {
     		FileInputStream fis = new FileInputStream(
					"C:\\Users\\LOQ\\eclipse-workspace\\vtiger-crm\\src\\test\\resources\\CommonData.properties");
			Properties pObj = new Properties();
			pObj.load(fis);

			String BROWSER = pObj.getProperty("bro");
			String URL = pObj.getProperty("url");
			String USERNAME = pObj.getProperty("un");
			String PASSWORD = pObj.getProperty("pwd");

			FileInputStream fis2 = new FileInputStream(
					"C:\\Users\\LOQ\\Desktop\\Bahubalis.xlsx");
			Workbook wb = WorkbookFactory.create(fis2);
			Sheet sh = wb.getSheet("Bahubalis");
			Row row = sh.getRow(3);
			Cell cell = row.getCell(0);
			String orgName = cell.getStringCellValue() + (int) (Math.random() * 999);

			// Employees
			Row row1 = sh.getRow(3);
			Cell cell1 = row1.getCell(1);
			int employees = (int) cell1.getNumericCellValue();

			// phone
			Row row2 = sh.getRow(3);
			Cell cell2 = row2.getCell(3);
			long phone = (long) cell2.getNumericCellValue();

			// billingCity
			Row row3 = sh.getRow(3);
			Cell cell3 = row3.getCell(2);
			String billcity = cell3.getStringCellValue();

			WebDriver driver = null;
			
			if(BROWSER.equals("chrome")) {
				driver=new ChromeDriver();
			}else if (BROWSER.equals("edge")) {
				driver=new EdgeDriver();
			}else if (BROWSER.equals("firefox")) {
				driver=new FirefoxDriver();
			}else {
				driver=new ChromeDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			driver.get(URL);

			// Login
			WebElement un = driver.findElement(By.name("user_name"));
			WebElement pwd = driver.findElement(By.name("user_password"));
			un.sendKeys(USERNAME);
			pwd.sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();

			// Create Organization------>>

			// 1.organization name
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();

			WebElement orgInputField = driver.findElement(By.name("accountname"));
			orgInputField.sendKeys(orgName);

			// 2.No.of Employees

			String empNo = Integer.toString(employees);

			WebElement empField = driver.findElement(By.id("employees"));
			empField.sendKeys(empNo);

			// 3.industry
			WebElement indDD = driver.findElement(By.name("industry"));
			Select selInd = new Select(indDD);
			int options = selInd.getOptions().size();
			int ranIndex = (int) (Math.random() * options);
			selInd.selectByIndex(ranIndex);
			String industry = selInd.getFirstSelectedOption().getText();

			// phone
			String phonenum = Long.toString(phone);
			WebElement phoneField = driver.findElement(By.id("phone"));
			phoneField.sendKeys(phonenum);

			// Rating
			WebElement ratingDD = driver.findElement(By.name("rating"));
			Select selRating = new Select(ratingDD);
			int options1 = selRating.getOptions().size();
			int ranNum = (int) (Math.random() * options1);
			selRating.selectByIndex(ranNum);
			String rating = selRating.getFirstSelectedOption().getText();

			// Billing City
			WebElement billField = driver.findElement(By.id("bill_city"));
			billField.sendKeys(billcity);

			// Save Button
			driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
			Thread.sleep(2000);

			// verification----->>

			// 1. for organization name
			String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
			if (actOrgName.equals(orgName)) {
				System.out.println("Organization Name Matched...");

			} else {
				System.out.println("Organization Name not Matched !!!!");

			}

			// 2.for Number of employees
			String actEmpno = driver.findElement(By.id("dtlview_Employees")).getText();
			if (actEmpno.equals(empNo)) {
				System.out.println("Employee number Matched !!!");
			} else {
				System.out.println("Employee number not Matched !!!");
			}

			// 3. for Industry dropdown
			String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
			if (actIndustry.equals(industry)) {
				System.out.println("Industry name Succesfully----->>>");
			} else {
				System.out.println("Industry name not matched !!!!");
			}
			
			//4. for phone
			String actPhoneNum=driver.findElement(By.id("dtlview_Phone")).getText();
			if (actPhoneNum.equals(phonenum)) {
				System.out.println("phone number matched----->>>");
			} else {
				System.out.println("Phone nuber not matched !!!!");
			}
			
			//5. for rating dropdown
			String actRating=driver.findElement(By.id("dtlview_Rating")).getText();
			if (actRating.equals(rating)) {
				System.out.println("Rating mathced....");
			} else {
				System.out.println("Rating not mathced....");
			}
			
			//6. for billing City
			String actBillCity=driver.findElement(By.id("dtlview_Billing City")).getText();
			if (actBillCity.equals(billcity)) {
				System.out.println("Billing city matched....");
			} else {
				System.out.println("Billing city not matched....");
			}

			// logout
			WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
			Actions act = new Actions(driver);
			act.moveToElement(profile).build().perform();
			driver.findElement(By.linkText("Sign Out")).click();
			
			driver.quit();

		}

	}



