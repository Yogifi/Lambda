package playground;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProgressBars {
	
	public WebDriver driver;
	
	@BeforeSuite
	public void suite()
	{
		System.out.println("I am before suite");
	}
	
	@BeforeClass
	public void setUp()
	{
	  System.setProperty("webdriver.chrome.driver","C:\\SeleniumTraining\\Workspace\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.lambdatest.com/selenium-playground");
		System.out.println("I am before CLAss");
	}
	
	// Test Scenario2- Drage the slider from 15 to 95
	@Test(priority =1,groups= {"sanity"})
	public void drag_drop() throws Exception
	{
	 driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();
	 WebElement range = driver.findElement(By.id("rangeSuccess"));
	 WebElement slide = driver.findElement(By.xpath("//input[@value='15']"));
	
	 Actions myaction = new Actions(driver);
			 myaction.dragAndDropBy(slide, 120, 0)
			 .build().perform();
		System.out.println("In Drag & Drop method");
	
	}
	
	@Test(priority =2,enabled= false)
	public void inputForm() throws Exception
	{
		
		//driver.navigate().back();
		driver.findElement(By.xpath("//a[text()='Input Form Submit']")).click();
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.getText();
		submit.click();
		Thread.sleep(3000);
		driver.findElement(By.id("name")).sendKeys("Yogi");
		driver.findElement(By.id("inputEmail4")).sendKeys("Yogi@yoga.com");
		driver.findElement(By.id("inputPassword4")).sendKeys("YogiTest");
		driver.findElement(By.id("company")).sendKeys("YogiBss");
		driver.findElement(By.id("websitename")).sendKeys("YogiBss");
		
		WebElement ctry = driver.findElement(By.xpath("//select[@name='country']"));
		Select country = new Select(ctry);
		country.selectByVisibleText("United States");
		
		
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("NewYork");
		driver.findElement(By.id("inputState")).sendKeys("NewYork");
		driver.findElement(By.id("inputZip")).sendKeys("323234");
		driver.findElement(By.id("inputAddress1")).sendKeys("Circle Road");
		driver.findElement(By.id("inputAddress2")).sendKeys("Circus Fort Road");
		
		submit.click();
		submit.getText();
		
		String msg = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
		String expMsg= "Thanks for contacting us, we will get back to you shortly.";
		
		Assert.assertEquals(msg, expMsg);
		
	}
	
	@Test (priority =3, groups= {"regression"})
	public void dropDown() throws Exception
	{
		driver.findElement(By.xpath("//a[text()='JQuery Select dropdown']")).click();
		
		WebElement ctry = driver.findElement(By.xpath("//select[@class='js-example-disabled-results select2-hidden-accessible']"));
		Select country = new Select(ctry);
		country.selectByVisibleText("Virgin Islands");
		
		Thread.sleep(3000);
		
		WebElement tech = driver.findElement(By.id("files"));
		Select files = new Select(tech);
		files.selectByVisibleText("Java");
		
	}
	
	@Test
	public void date() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='JQuery Date Picker']")).click();
		driver.findElement(By.id("from")).click();	
		WebElement smonths=driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		Select smonth = new Select(smonths);
		smonth.selectByValue("6");
		
		driver.findElement(By.xpath("//a[text()='13']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.id("to")).click();
		WebElement tmonths=driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		Select tmonth = new Select(tmonths);
		tmonth.selectByValue("11");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='13']")).click();

	}
	
	@Test(enabled= false)
	public void table() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='Table Data Search']")).click();
		
		driver.findElement(By.xpath("//button[text()='Filter']")).click();
		driver.findElement(By.xpath("//input[@placeholder='#']")).sendKeys("5");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='#']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("QA");
		
	}
	
	@Test(enabled=false)
	public void download() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='JQuery Download Progress bars']")).click();
		
		driver.findElement(By.id("downloadButton")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@type='button' and text()='Cancel Download']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("downloadButton")).click();
		
		
 List<WebElement> exit = driver.findElements(By.xpath("//button[text()='Close' and @type='button']"));
		
		WebElement close = driver.findElement(By.xpath("//button[text()='Close' and @type='button']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(close));
		
		
		
		String msg = driver.findElement(By.xpath("//div[@class='progress-label']")).getText();
		
		Assert.assertEquals(msg,"Complete!");
		close.click();
		
		
	}
	
	@Test
	public void modal() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='Bootstrap Modals']")).click();
		
		//driver.findElement(By.id("downloadButton")).click();
		
		List<WebElement> modal = driver.findElements(By.xpath("//button[text()='Launch Modal']"));
	
		 modal.get(1).click();
		 Thread.sleep(2000);
		 modal.get(2).click();
		 Thread.sleep(2000);
		 List<WebElement> save = driver.findElements(By.xpath("//button[text()='Save Changes']"));
		 save.get(2).click();
		 Thread.sleep(2000);
		 List<WebElement> close = driver.findElements(By.xpath("//button[text()='Close']"));
		 close.get(1).click();
		 
		 
}
	
	@Test(enabled=false)
	public void window() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='Window Popup Modal']")).click();
		String title = driver.getTitle();
		driver.findElement(By.id("followboth")).click();
		
		Set<String>handle = driver.getWindowHandles();
		
		for(String h:handle)
		{
			driver.switchTo().window(h);
			Thread.sleep(2000);
			if(!(driver.getTitle().equals(title)))
				driver.close();
			
		}
	}
	
	@Test(groups={"sanity","regression"})
	public void hover() throws Exception
	{
		
		driver.findElement(By.xpath("//a[text()='Hover Demo']")).click();
		
		List<WebElement>hover = driver.findElements(By.xpath("//div[text()='Hover Me']"));
	   
		Actions myaction = new Actions(driver);
		Thread.sleep(3000);
	    myaction.moveToElement(hover.get(0)).build().perform();
	    hover.get(0).getCssValue(null);
	    System.out.println("I am HOVER");
	}
	
	@AfterClass
	public void tearDown() throws Exception
	{
		driver.quit();
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		System.out.println("I am AFter CLASS");
	}
	
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("I am after suite");
	}
	
	
}
	
