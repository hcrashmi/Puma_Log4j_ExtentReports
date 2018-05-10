package com.puma.pomClass1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGNITEevoKNITPage 
{
	public WebDriver driver;
	
	//Xpath for selecting size
	@FindBy(xpath="//div[@class='product-size-click-btn']")
	private WebElement size;
	
	//Xpath for selecting size as 8
	@FindBy(xpath="//a[@name='8']/span[contains(.,'8')]")
	private WebElement selectSize;
		
	//Xpath for add to cart
	@FindBy(xpath="//button[@title='Add to Cart']")
	private WebElement addToCart;
	
	public IGNITEevoKNITPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	//Adding product to cart
	public void addToCart() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.visibilityOf(size)); 
	    wait.until(ExpectedConditions.elementToBeClickable(size));
		size.click();
		selectSize.click();
		Thread.sleep(5000);
		addToCart.click();
		Thread.sleep(5000);
	}
}
