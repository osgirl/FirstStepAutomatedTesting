package org.apache.maven.Email;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Verification {
	
	//all fields to be verified within the email
	String name, address, phone, email, down, annual, debts, financing, citizen, alien, employ, repay, credit;
	
	public Verification() {
		this.name = "Verification";
		this.address = "Verification";
		this.phone = "Verification";
		this.email = "Verification";
		this.down = "Verification";
		this.annual = "Verification";
		this.debts = "Verification";
		this.financing = "Verification";
		this.citizen = "Verification";
		this.alien  = "Verification";
		this.employ = "Verification"; 
		this.repay = "Verification"; 
		this.credit = "Verification";
	}
	
	public String verifier(String dir) throws Exception {
		//error will == initial if error is not changed by capture
		String error = "initial";
		
		//points to the most recently modified 
		//file in the directory 'dir'
		File fp = lastFileModified(dir);
		
		EmailRead reader = new EmailRead(fp);
		EmailCompare ec;
		
		try {
			//set and print any errors occurring in
			//reader construction
			//error = reader.capture();
			reader.test();
			System.out.println(error);
			
			//set and print any mismatches found
			//by tester
			ec = new EmailCompare(reader);
			error = ec.tester();
			System.out.println("Fields with Mismatches:" + error);
		}
		catch(IOException Exception) {
		}
		
		return error;
	}
	
	//set the "base" information set in questionnaire
	//name contains both first and last name
	//address contains zip, city, state, street
	public void setBase(String email, String phone, String name, String addr) {
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = addr;
	}
	
	//I will be hard coding 'yes' in financing
	//when running through multiple instances
	//this will need to be changed in the function CALL
	public void setFinance(String financing) {
		this.financing = financing;
	}
	
	
	//setting citizen, alien, employ, and repay
	//this should only occur if financing is 'yes'
	//all hardcoded to No in function CALL
	public void setYes_No(String citizen, String alien, String employ, String repay) {
		this.citizen = citizen;
		this.alien = alien;
		this.employ = employ;
		//NOTE: this is found after debts in eml
		this.repay = repay;
	}
	
	//setting the values of our financials within the verification obj
	//credit is hardcoded in the function CALL
	public void setFinancials(String down, String annual, String debts, String credit) {
		this.down = down;
		this.annual = annual;
		this.debts = debts;
		this.credit = credit;
	}
	
	//returns most recently modified File in a directory
	public static File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
}
