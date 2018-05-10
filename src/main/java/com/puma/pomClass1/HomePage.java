package com.puma.pomClass1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	public WebDriver driver;
	
	//Xpath for Mouse hover action on Men menu
	
	@FindBy(xpath="//a[@data-subnav='men-subnav']")
	private WebElement men;
	
	//Xpath for Shoes
	@FindBy(xpath="//a[@href='https://in.puma.com/men/shoes.html?dir=asc&order=position&in-stock=1']")
	private WebElement shoes;
	
	//Xpath for Running Shoes
	@FindBy(xpath="//a[@href='https://in.puma.com/men/shoes/running.html/?dir=asc&order=position&in-stock=1']")
	private WebElement runningShoes;
	
	////Xpath for second shoes from list
	@FindBy(xpath="//ul[@class='products-grid products-grid--max-4-col first last odd']/li[2]/a[@class='product-image promainimg']")
	private WebElement secondShoes;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	//Navigating through pages like men,Shoes,Running Shoes and Clicking on Second shoes from the list	
	public void menShoe(int a) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(men).build().perform();
		Thread.sleep(5000);
		shoes.click();
		runningShoes.click();
		driver.findElement(By.xpath("//ul [@class='products-grid products-grid--max-4-col first last odd']/li["+a+"]/a[@class='product-image promainimg']"));
		secondShoes.click();
		
	}
}
