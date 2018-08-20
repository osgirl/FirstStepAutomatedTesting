package org.apache.maven.Pages;

import org.apache.maven.firstStep.*;
import org.openqa.selenium.WebDriver;

public class FinancialsPage extends BasicPageAction{
	String downP, annualIn, monthlyD;
	
	public FinancialsPage(Integer downP, Integer annualIn, Integer monthlyD) {
		this.downP = downP.toString();
		this.annualIn = annualIn.toString();
		this.monthlyD = monthlyD.toString();
	}
	
	public void clearFinancialsPage(WebDriver driver) {
		clearFieldByName(driver, "down-payment");
		clearFieldByName(driver, "annual-income");
		clearFieldByName(driver, "monthly-debts");
	}
	
	public void setFinancialsPage(WebDriver driver) {
		try {
			Thread.sleep(3000);
			setFieldByName(driver, "down-payment", downP);
			setFieldByName(driver, "annual-income", annualIn);
			setFieldByName(driver, "monthly-debts", monthlyD);
		}
		
		catch (InterruptedException Exception) {
			System.out.println("Something Went Wrong with setting financials");
		}
	}
	
	public void nextPage(WebDriver driver) {
		clickElementByX(driver,"//div[@ng-controller='FinancialsCtrl']//wizard-step-content//div[@class='container-fluid scroller']//div[@class='row']//form[@name='wizardStepForm']//div[@class='nav-buttons']//button[@ng-if='next']");
	}
}
