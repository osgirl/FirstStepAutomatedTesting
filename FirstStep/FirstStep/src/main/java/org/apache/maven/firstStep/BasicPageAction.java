package org.apache.maven.firstStep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BasicPageAction {
	
	public WebDriver driver;
	
	public BasicPageAction() {
		
	}
	
	//'i' is not used. it simply differentiates this constructor
	//from the 'no args constructor'. This prevents multiple windows
	//from being opened when creating the different pages
	public BasicPageAction(int i) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kchang\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	//two options for clicking elements
	//By X (xpath) or By Css (CSS Selector)
	//When one throws 'is not visible' error try the other 
	public void clickElementByX(WebDriver driver, String xpass) {
		driver.findElement(By.xpath(xpass)).click();
	}
	
	public void clickElementByCss(WebDriver driver, String css) {
		driver.findElement(By.cssSelector(css)).click();
	}
	
	//Set Text Field by text fields 'name'
	public void setFieldByName(WebDriver driver, String name, String entry) {
		driver.findElement(By.name(name)).sendKeys(entry);
	}
	
	public void clearFieldByName(WebDriver driver, String name) {
		driver.findElement(By.name(name)).clear();
	}
	
	//click on the radio button passed and then click on the next step button
	//if the button does not appear on the page you will get an 'element not found' exception
	public int setRadioAndNext(WebDriver driver, String radio, String button, String error, int perm) throws InterruptedException {
		try {
			Thread.sleep(3000);
			clickElementByX(driver, radio);
			if(radio.equals("//html//div[@ng-controller='CitizenCtrl']//div[2]/label[1]")) {
				citizenSecondary(driver, error, perm);
			}
			clickElementByX(driver, button);
		}
		
		catch(InterruptedException Exception) {
			System.out.println("Something went wrong while setting " + error);
		}
		
		if(radio.equals("//label[@class='no']"))
			return -1;
		else
			return 1;
	}
	
	public void citizenSecondary(WebDriver driver, String error, int perm) throws InterruptedException {
		try {
			Thread.sleep(3000);
			if(perm == 1) 
				clickElementByX(driver, "//html//div[2]/div[1]/label[1]");
			
			else
				clickElementByX(driver, "//html//div[2]/div[2]/label[1]");
		}
		
		catch(InterruptedException Exception) {
			System.out.println("Something went wong with " + error);
		}
	}
	
	
	//returns the webdriver used for whole application
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDropDown(WebDriver driver, String eleName, int ind) {
		WebElement mySelectElement = driver.findElement(By.name(eleName));
		Select dropdown = new Select(mySelectElement);
		dropdown.selectByIndex(ind);
	}
	
	public int disclosure(WebDriver driver, String selection) throws InterruptedException {
		try {
			Thread.sleep(3000);
			clickElementByX(driver, selection);
		}
		
		catch(InterruptedException Exception) {
			System.out.println("Something went wrong with the disclosure");
		}
		
		if(selection.equals("//button[@class='btn btn-default']"))
			return 1;
		
		else
			return 0;
	}
	
}

