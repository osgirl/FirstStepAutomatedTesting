package org.apache.maven.Email;



public class EmailCompare extends Verification{
	String name = "", 
			address = "", 
			phone = "", 
			email = "", 
			down = "", 
			annual = "", 
			debts = "", 
			financing = "", 
			citizen = "", 
			alien = "", 
			employ = "", 
			repay = "", 
			credit = "";
	EmailRead reader;
	
	public EmailCompare(EmailRead reader) {
		this.reader = reader;
	}
	
	//checking fields recorded in EmailRead
	//vs what was input in FirstStep
	//Returns all mismatched fields
	public String tester() {
		if(!(reader.name.equals(super.name))) {
			name = "name ";
		}
		
		if(!(reader.address.equals(super.address))) {
			address = "address ";
		}
		
		if(!(reader.phone.equals(super.phone))) {
			phone = "phone ";
		}
		
		if(!(reader.email.equals(super.email))) {
			email = "email ";
		}
		
		if(!(reader.down.equals(super.down))) {
			down = "down ";
		}
		
		if(!(reader.annual.equals(super.annual))) {
			annual = "annual ";
		}
		
		if(!(reader.debts.equals(super.debts))) {
			debts = "debts ";
		}
		
		if(!(reader.financing.equals(super.financing))) {
			financing = "financing ";
		}
		
		if(!(reader.citizen.equals(super.citizen))) {
			citizen = "citizen ";
		}
		
		if(!(reader.alien.equals(super.alien))) {
			alien = "alien ";
		}
		
		if(!(reader.employ.equals(super.employ))) {
			employ = "employ ";
		}
		
		if(!(reader.repay.equals(super.repay))) {
			repay = "repay ";
		}
		
		if(!(reader.credit.equals(super.credit))) {
			credit = "credit ";
		}
		
		return name + address + phone + email + down + annual + debts + financing + citizen + alien + employ + repay + credit;
	}
}
