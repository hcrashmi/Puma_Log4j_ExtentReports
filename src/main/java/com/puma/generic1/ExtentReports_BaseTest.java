package com.puma.generic1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReports_BaseTest implements AutoConstant
{
	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentTest tests;

	static
	{
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	
	@BeforeSuite//(alwaysRun = true)
	public void init()
	{	
		String path = "./TCReports/TcReport.html";
		reports=new ExtentReports(path);
		
	}
	
	@BeforeClass
	public void classTest(){
		driver = new FirefoxDriver();
		driver.get("https://in.puma.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	@BeforeMethod//(alwaysRun = true)
	public void preCondition(Method testName)
	{
		String name = testName.getName();
		System.out.println(name);
		tests=reports.startTest(name);
		
		
	}
	
	@AfterMethod//(alwaysRun = true)
	public void postCondition(ITestResult res, Method method) throws IOException
	{
		int s = res.getStatus();
		if(s==1)
		{
			tests.log(LogStatus.PASS, "pass");
		}
		else if(s!=1)
		{
			tests.log(LogStatus.FAIL, "faill");
			String dateTime = new Date().toString().replaceAll(":", "_");
			TakesScreenshot t = (TakesScreenshot) driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			File dstFile = new File("./NewFailResults/res1" + method.getName()+dateTime+".png");
			FileUtils.copyFile(srcFile, dstFile);
		}
		
		reports.endTest(tests);
		
	}
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	
	
	@AfterSuite//(alwaysRun = true)
	public void end()
	{
		reports.flush();
		//driver.close();
	}
}
