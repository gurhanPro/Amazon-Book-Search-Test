package com.gurhanpro.amazonTest.AmazanTest;



import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class AmazonTest {
	private WebDriver driver;
	
  @Test
  public void f() {
	  WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
	  Select searchDropdownBox = new Select(dropdown);
	  searchDropdownBox.selectByVisibleText("Books");
	  
	  WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
	  searchBar.sendKeys("Test Automation  best practices:"
	  		+ " \"Won the Best Automation Book Award in TestKit 2014\"");
	  WebElement submit = driver.findElement(By.className("nav-input"));
	  submit.click();
	 // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  Boolean isFound = new WebDriverWait(driver,15).until(
			  new ExpectedCondition<Boolean>(){

				public Boolean apply(WebDriver arg0) {
					
					return driver.findElement(By.id("resultsCol")).isEnabled();
				}});
	  // assert that the page loaded
	  assertTrue(isFound, "the search results page did not load");
	  WebElement bookTitle = driver.findElement(By.xpath("//h2[@data-attribute='Test Automation  best"
	  		+ " practices: \"Won the Best Automation Book Award in TestKit 2014\"\']"));
	  assertEquals( bookTitle.getText(),"Test Automation  best practices:"
	  		+ " \"Won the Best Automation Book Award in TestKit 2014\"","coudn't find book title");
  }
  
@BeforeMethod
  public void beforeMethod() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(35,TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://amazon.com/");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
