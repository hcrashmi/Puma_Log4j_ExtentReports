package com.puma.script1;

import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.puma.generic1.BaseTest;
import com.puma.generic1.ExtentReports_BaseTest;
import com.puma.pomClass1.HomePage;
import com.puma.pomClass1.IGNITEevoKNITPage;

//import demo.Log4jHtmlLayoutTest;


public class AddProductTest extends ExtentReports_BaseTest
{
	//static final org.apache.log4j.Logger logger = LogManager.getLogger(AddProductTest.class.getName());
	static Logger logger = Logger.getLogger(AddProductTest.class.getName());
	@Test
	public void addProduct() throws InterruptedException
	{
		//DOMConfigurator.configure("Log4j.xml");
		PropertyConfigurator.configure("./Log4j.properties");
	     logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
	     logger.info("TEST Has Started");
		
		//Title matching
	     logger.info("Retrieving title and matching with expected title");
	     Assert.assertEquals(driver.getTitle(),"Buy Sports T-Shirts, Tracks, Running Shoes and Accessories Online - in.puma.com");
		Reporter.log("Titles are same",true);
		
		//Creating object for HomePage
		HomePage hp=new HomePage(driver);
		hp.menShoe(2);
		logger.info(" Mouse hover action done on Men");
		logger.info("Clicked on Shoes");
		logger.info("Clicked on Running shoes");
		logger.info("Clicked on second Shoes from list");
		
		//Switching between tabs
		logger.info("Switching tabs");
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		
		for(String wh:allWindows)
		{
			driver.switchTo().window(wh);
			
		}
		
		//Creating object for IGNITEevoKNITPage
		IGNITEevoKNITPage ip=new IGNITEevoKNITPage(driver);
		ip.addToCart();
		logger.info("Clicking on Size");
		logger.info("Select the size");
		logger.info("Clicked on add to cart");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.titleContains("Shopping Cart"));
		logger.info("Wait untill page title matches");
		
		String productName=driver.findElement(By.xpath("//table[@id='shopping-cart-table']//h2/a")).getText();
		System.out.println(productName);
		
		//Verifying text using assert
		Assert.assertEquals(productName,"IGNITE EvoKNIT Lo 2 Men's Running Shoes");
		Reporter.log("text IGNITE EvoKNIT Lo 2 Men's Running Shoes is present",true);
		logger.info("Text matched");
		
		String value1 = driver.findElement(By.xpath("//td//select/option[@selected='selected']")).getText();
		System.out.println(value1);
		Assert.assertEquals(value1, "1");
		Reporter.log("Quantity one is present",true);
		logger.info("Quantity one is present");
		
	}
}
