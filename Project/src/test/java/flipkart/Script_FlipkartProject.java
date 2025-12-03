
package flipkart;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Script_FlipkartProject {
	@Test
	public void suite() throws InterruptedException, AWTException, IOException {
		//Disable Notification Popup
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		
		//Launch the browser
				
		WebDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.flipkart.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		//Search for the product
		WebElement search = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement searchbtn = driver.findElement(By.xpath("//button[@type='submit']"));
		search.sendKeys("Bluetooth Speakers");				
		searchbtn.click();
		
		//Filter the brand
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Brand']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='boAt']"))).click();
		Thread.sleep(2000);		//Why Thread.sleep() means while filtering the product the page is refreshing so i used there
				
		//Filter the rating
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='4★ & above']"))).click();
		Thread.sleep(2000);
				
		//Sort results by Price — Low to High.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Price -- Low to High']"))).click();
		Thread.sleep(2000);
				
		//Select the product
		Actions a = new Actions(driver);
		WebElement product = driver.findElement(By.xpath("(//div[@class='RGLWAk'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(product));
		
		//Open into new tab
		a.contextClick(product).perform();
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
				
		//Switch to the product tab
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			if (!driver.getWindowHandle().equals(tab)) {
				driver.switchTo().window(tab);
			}
		}
				
		//Check for the Available offers
		
		WebElement offer = driver.findElement(By.xpath("//div[.='Available offers']"));
		wait.until(ExpectedConditions.visibilityOf(offer));
		if (offer.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/div"))).click();
			List<WebElement> offers = driver.findElements(By.xpath("//div[.='Available offers']/..//span/li"));
				System.out.println("Number of offers:- "+offers.size());
		}
		else
		{
			System.out.println("There is no available offers");
		}
		
		//Add to cart and take screenshot
		WebElement cartbtn = driver.findElement(By.xpath("//button[.='Add to cart']"));
		
		boolean outOfStock = false;

		try {
		    driver.findElement(By.xpath("//*[contains(text(),'Out of stock')"));
		    outOfStock = true;
		} catch (Exception e) {
		    outOfStock = false;
		}
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		
		if ( !cartbtn.isDisplayed() || !cartbtn.isEnabled() || outOfStock) {
				
			System.out.println("Product is unavailable");

	         TakesScreenshot ts = (TakesScreenshot) driver;
	         File temp = ts.getScreenshotAs(OutputType.FILE);
	         File perm = new File("./Screenshot/result.png");
	         FileHandler.copy(temp, perm);
	         System.out.println("Screenshot is saved into screenshot folder");
	         driver.quit();
			}
		else {
			System.out.println("Product id available & Product is added to the cart");
			WebElement cart = driver.findElement(By.xpath("//button[.='Add to cart']"));
			wait.until(ExpectedConditions.visibilityOf(cart));
			cart.click();
			Thread.sleep(2000);
			//take screenshot of the page
			TakesScreenshot ts = (TakesScreenshot)driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			File perm = new File("./Screenshot/cart_result.png");
			FileHandler.copy(temp, perm);
			System.out.println("Screenshot is saved into screenshot folder");
			driver.quit();
		}
	}
}
