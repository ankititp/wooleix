package stepDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Brokers {

	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Given("^I visit shopping Website$")
	public void test() {
		System.setProperty("webdriver.chrome.driver", "/Users/ankit/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php/");
		driver.manage().window().maximize();
	}

	@When("^I click on sign in and login$")
	public void sigin() throws InterruptedException {
		driver.findElement(By.className("login")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys("ankititp@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("testing");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#SubmitLogin")).click();
	}

	@Then("^I click on the Dresses tab and do shopping$")
	public void dresses() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement first=driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li//span[contains(text(), \"Add to cart\")]"));
		js.executeScript("arguments[0].click()",first);	
		Thread.sleep(2000);
	}

	@When("^I click proceed to checkout$")
	
	public void add_two_items() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Continue= driver.findElement(By.xpath("//span[@title=\"Continue shopping\"]"));
		js.executeScript("arguments[0].click()",Continue);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]")).click();
	
		WebElement second=driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li//span[contains(text(), \"Add to cart\")]"));
		js.executeScript("arguments[0].click()",second);	
		Thread.sleep(2000);
		WebElement Proceed= driver.findElement(By.xpath("//span[contains(text(),\"Proceed to checkout\")]"));
		js.executeScript("arguments[0].click()",Proceed);
		Thread.sleep(2000);
		js.executeScript("scroll(0, 550);");
		//Shopping cart summary
		WebElement Cart_Proceed=driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]/span"));
		js.executeScript("arguments[0].click()",Cart_Proceed);
	}

	@Then("^I will complete payment$")
	public void complete() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, 550);");
		driver.findElement(By.xpath("//button[@type=\"submit\"]/span[contains(text(), \"Proceed to checkout\")]")).click();
		//driver.findElement(By.xpath("//p[contains(text(), \"You must agree to the terms of service before continuing.\")]")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath("//button[@type=\"submit\"]/span[contains(text(), \"Proceed to checkout\")]")).click();
		//driver.findElement(By.xpath("//a[@class=\"fancybox-item fancybox-close\"]"));
		js.executeScript("scroll(0, 550);");
		driver.findElement(By.xpath("//a[@title=\"Pay by check.\"]")).click();;
		driver.findElement(By.xpath("//button[@type=\"submit\"]/span[contains(text(), \"I confirm my order\")]")).click();;
		
	}

	@Then("^Verify the complete succesful order$")
	public void sucessMessage() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		String sucess=driver.findElement(By.xpath("//p[contains(text(), \"Your order on My Store is complete.\")]")).getText();
		Assert.assertEquals(sucess,"Your order on My Store is complete.");
		Your order on My Store is complete.
		driver.quit();
	}

}
