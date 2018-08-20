package org.apache.maven.Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.jsoup.Jsoup;

public class EmailRead {
	
	//all fields to be verified within the email
	String name, address, phone, email, down, annual, debts, financing, citizen, alien, employ, repay, credit;
	File fp;
	EmailCompare ec;
	
	public EmailRead(File fp) {
		this.name = "Email Reader";
		this.address = "Email Reader";
		this.phone = "Email Reader";
		this.email = "Email Reader";
		this.down = "Email Reader";
		this.annual = "Email Reader";
		this.debts = "Email Reader";
		this.financing = "Email Reader"; 
		this.citizen = "Email Reader";
		this.alien  = "Email Reader";
		this.employ = "Email Reader"; 
		this.repay = "Email Reader"; 
		this.credit = "Email Reader";
		
		this.fp = fp;
	}
	
	//parses the Email printing contents without HTML Tags
	//this is set up for determining if data entered in First Step
	//is accurately recorded in eml files
	//https://stackoverflow.com/questions/24772828/how-to-parse-html-table-using-jsoup
	//above link allows for better implementation of eml parsing
	public void test() throws Exception {
		String content;
		
		Properties props = System.getProperties();
		props.put("mail.host", "smtp.dummydomain.com");
		props.put("mail.transpot.protocol", "smtp");
		
		Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(fp);
        MimeMessage message = new MimeMessage(mailSession, source);

        //using jsoup to parse eml file working around html tags
//        content = (String) message.getContent();
//        content = Jsoup.parse(content).text();

        System.out.println("Subject : " + message.getSubject());
        System.out.println("From : " + message.getFrom()[0]);
        System.out.println("--------------");
        System.out.println("Body : " +  message.getContent());
	}
	
	//the controller for EmailRead. This method handles the entire
	//functionality of EmailRead
	public String capture() throws IOException{
		BufferedReader br = null;
		String holder;
		
		try {
			br = new BufferedReader(new FileReader(fp));
			
			holder = br.readLine();
			
			//first line should be Title
			if(holder != null){
				br.readLine();
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading first line";
			
			//now we start collecting data from the email
			//need to clean this its hideous
			if(holder != null) {
				name = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading name";
			
			if(holder != null) {
				address = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading address";
			
			if(holder != null) {
				phone = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading phone";
			
			if(holder != null) {
				email = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading email";
			
			if(holder != null) {
				financing = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading financing";
			
			if(holder != null && financing.equals("Yes")) {
				return "capture ran successfully1";
			}	
			
			if(holder != null) {
				credit = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading credit";
			
			if(holder != null) {
				citizen = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading citizen";
			
			if(holder != null) {
				alien = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading alien";
			
			if(holder != null) {
				employ = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading employ";
			
			if(holder != null) {
				down = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading down";
			
			if(holder != null) {
				annual = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading annual";
			
			if(holder != null) {
				debts = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading debts";
			
			if(holder != null) {
				repay = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading repay";
			
			if(holder != null) {
				credit = holder;
				holder = br.readLine();
				System.out.println(holder);
			}
			else
				return "Something went wrong reading repay";
		}
		
		catch(IOException Exception) {
			System.out.println("Something went wrong with capture");
		}
		
		finally {
			br.close();
		}
		return "capture ran successfully";
	}
}
