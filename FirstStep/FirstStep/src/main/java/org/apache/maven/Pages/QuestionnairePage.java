package org.apache.maven.Pages;

import org.apache.maven.firstStep.*;
import org.openqa.selenium.WebDriver;

public class QuestionnairePage extends BasicPageAction {
	
	String email, phone, first, last, addr, city, State, zip;
	int index;
	
	public QuestionnairePage(String email, String phone, String first, String last, String addr, String city, String State, String zip, int index) {
		this.email = email;
		this.phone = phone;
		this.first = first;
		this.last = last;
		this.addr = addr;
		this.city = city;
		this.State = State;
		this.zip = zip;
		this.index = index;
	}
	
	public void clearQuestionnairePage(WebDriver driver) {
		clearFieldByName(driver, "email");
		clearFieldByName(driver,"phone");
		clearFieldByName(driver,"primary-first-name");
		clearFieldByName(driver,"primary-last-name");
		clearFieldByName(driver,"address-1");
		clearFieldByName(driver,"city");
		clearFieldByName(driver,"state");
		clearFieldByName(driver,"zipcode");
	}
	
	public void setQuestionnairePage(WebDriver driver) {
		setFieldByName(driver, "email", email);
		setFieldByName(driver, "phone", phone);
		setFieldByName(driver, "primary-first-name", first);
		setFieldByName(driver, "primary-last-name", last);
		setFieldByName(driver, "address-1", addr);
		setFieldByName(driver, "city", city);
		setFieldByName(driver, "state", State);
		setFieldByName(driver, "zipcode", zip);
		setDropDown(driver, "country", index);
	}
	
	public void nextPage(WebDriver driver) {
		clickElementByX(driver,"//div/div/div/div/div/div/section/ng-include/div/wizard-step-content/div/div/form/div/button");
	}
}