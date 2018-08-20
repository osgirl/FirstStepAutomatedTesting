package org.apache.maven.firstStep;

import org.apache.maven.Pages.*;

import java.io.IOException;

import org.apache.maven.Email.*;

/**
 * @author Kieran Chang
 * 		   Toll Brothers IT Intern Summer 2018
 *
 * First Step Main Class
 */


/* There are several places in this class and in the rest of the project
 * that use 'sleeps'. These are used in order to account for latency
 * between the application and the web page.
 * Without them the application will crash.
 */
public class FirstStep {

	public static void main(String[] args) throws Exception{
		//entries for the questionnaire page
		String email = "1234@1234.com", 
				phone = "123-456-1234", 
				first = "Tester", 
				last = "Testing", 
				addr = "1234 Main St.", 
				city = "Horsham", 
				state = "PA", 
				zip = "12345";
		
		//entries for the financials page
		Integer downPay = 1,
				annualInc = 1,
				monthlyDebts = 1;
		
		int choice = 0,
				index = 10,
				i = 0;
		
		//NOTE: if financing is set to No the program will skip over the other
		//'setRadioAndNext' calls and submit the disclosure
		//financing options
		String financingYes = "//label[@class='yes']",
				financingNo = "//label[@class='no']",
				financingBtn = "HTML/BODY/DIV/DIV/DIV/DIV/DIV/DIV[8]/SECTION[2]/NG-INCLUDE/DIV/WIZARD-STEP-CONTENT/DIV/DIV[2]/FORM/DIV[2]/BUTTON[2]";
		
		//credit options
		String creditExcellent = "//html//div[@ng-controller='CreditCtrl']//div[@ng-if='input.shouldHide !== true']/div[1]/label[1]",
				creditGood = "//html//div[@ng-controller='CreditCtrl']//div[2]/label[1]",
				creditNormal = "//html//div[@ng-controller='CreditCtrl']//div[3]/label[1]",
				creditBelowAvg = "//html//div[@ng-controller='CreditCtrl']//div[4]/label[1]",
				creditNA = "//html//div[@ng-controller='CreditCtrl']//div[5]/label[1]",
				creditBtn = "//div[@ng-controller='CreditCtrl']//wizard-step-content//div[@class='container-fluid scroller']//div[@class='row']//form[@name='wizardStepForm']//div[@class='nav-buttons']//button[@ng-if='next']";
		
		//US Citizen Options
		String citizenYes = "//html//div[@ng-controller='CitizenCtrl']//div[@ng-if='input.shouldHide !== true']/div[1]/label[1]",
				citizenNo = "//html//div[@ng-controller='CitizenCtrl']//div[2]/label[1]",
				citizenBtn = "//div[@ng-controller='CitizenCtrl']//wizard-step-content//div[@class='container-fluid scroller']//div[@class='row']//form[@name='wizardStepForm']//div[@class='nav-buttons']//button[@ng-if='next']";
		
		//US Employment options
		String self = "//html//div[@ng-controller='EmploymentCtrl']//div[@ng-if='input.shouldHide !== true']/div[1]/label[1]",
				notSelf = "//html//div[@ng-controller='EmploymentCtrl']//div[2]/label[1]",
				employmentBtn = "//div[@ng-controller='EmploymentCtrl']//wizard-step-content//div[@class='container-fluid scroller']//div[@class='row']//form[@name='wizardStepForm']//div[@class='nav-buttons']//button[@ng-if='next']";
		
		//US Repayments options
		String repayNo = "//html//div[@ng-controller='RepaymentsCtrl']//div[2]/label[1]",
				repayYes = "//html//div[@ng-controller='RepaymentsCtrl']//div[@ng-if='input.shouldHide !== true']/div[1]/label[1]",
				repayBtn = "//button[@ng-if='finish']";
		
		//Disclosure options
		String disclose = "//button[@class='btn btn-default']",
				goBack = "//a[@href='#/wizard']";
		
		//all objects
		QuestionnairePage qp = new QuestionnairePage(email, phone, first, last, addr, city, state, zip, index);
        FinancialsPage fp = new FinancialsPage(downPay, annualInc, monthlyDebts);
		
		//setting scene for rest of application
		//the application has a tendency to crash on the first
		//use or if it has not been run recently.
		//It will work on the second or third try.
        BasicPageAction basic = new BasicPageAction(1);
		basic.driver.get("https://firststep.tolltest.com/#/home/01171");
        basic.driver.manage().window().maximize();
        
        //opening questionnaire
        Thread.sleep(3000);
        basic.clickElementByX(basic.driver, "//div/div/div/div/div/div[4]/div[3]/a");
        
        do {
	        //filling out questionnaire page then moving on
	        Thread.sleep(3000);
	        //ensure the questionnaire is clear before entering data
	        qp.clearQuestionnairePage(basic.driver);
	        qp.setQuestionnairePage(basic.driver);
	        Thread.sleep(1000);
	        qp.nextPage(basic.driver);
	        
	        //for changing the input on 'setRadioAndNext' change the second parameter passed
	        //to one of the other variables in each respective category
	        //set financing
	        int holder = basic.setRadioAndNext(basic.driver, financingYes, financingBtn, "financing", 0);
	        
	        if(holder == 1) {
	        
		        //set credit
		        basic.setRadioAndNext(basic.driver, creditExcellent, creditBtn, "credit", 0);
		        
		        //set US Citizen
		        basic.setRadioAndNext(basic.driver, citizenNo, citizenBtn, "citizen", 0);
		        
		        //set Employment
		        basic.setRadioAndNext(basic.driver, notSelf, employmentBtn, "Employment", 0);
		        
		        //set Financial
		        Thread.sleep(3000);
		        fp.clearFinancialsPage(basic.driver);
		        fp.setFinancialsPage(basic.driver);
		        Thread.sleep(1000);
		        fp.nextPage(basic.driver);
		        Thread.sleep(1000);
		        
		        //set repayments
		        basic.setRadioAndNext(basic.driver, repayNo, repayBtn, "repayments", 0);
		        
		        Verification ver = new Verification();
		        ver.setBase(email, phone, first + last, addr + state + city + zip);
		        ver.setFinance("Yes");
		        ver.setFinancials(downPay.toString(), annualInc.toString(), monthlyDebts.toString(), "Excellent (FICO Score 780 and above) ");
		        ver.setYes_No("No", "No", "No", "No");
		        
		        i++;
	        }
	        
	        //set disclosure
	        choice = basic.disclosure(basic.driver, disclose);
        }while(choice != 1 && i < 5);
        
        basic.driver.close();
        
        Verification ver = new Verification();
        ver.verifier("\\\\hqfsat01.tbtest.tolltest.com\\data\\FirstStep\\Emails");
	}

}
