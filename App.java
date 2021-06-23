package com.lcwc;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {

		System.out.println("Prepare to send msg.......");
		String message = "hello cbcz...this is msg for security check .";
		String subject = "MSG FROM CK : TESTING ";
		String to = "indiantourist87@gmail.com";
		String from = "chetankagda238@gmail.com";

		//sendEmail(message, subject, to, from);
		sendAttach(message,subject,to,from);

	}
	
	//this is responsible to send the msg with attechment.
	private static void sendAttach(String message, String subject, String to, String from) {
		String host="smtp.gmail.com";
		
		//get the system properties
		
		Properties p=System.getProperties();
		System.out.println("PROPERTIES :"+p);
		
		//setting important information to the properties
		
		p.put("mail.smtp.host",host);
		p.put("mail.smtp.port","465");
		p.put("mail.smtp.ssl.enable","true");
		p.put("mail.smtp.auth","true");
		
		// step 1:to get the session object.

		Session session = Session.getInstance(p, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("chetankagda238@gmail.com", "8320401936");
			}

		});
		session.setDebug(true);

		// step 2 : compose the msg [TEXT]

		MimeMessage m = new MimeMessage(session);

		try {
			
			// from email id
			m.setFrom(from);

			// to email id
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
			//adding subject to msg
			m.setSubject(subject);
			
			//attachment
			//file path
			String path="C:\\Users\\MKCK\\Desktop\\cj.jpg";
			
			MimeMultipart mm =new MimeMultipart();
			//text
			//file
			MimeBodyPart text=new MimeBodyPart();
			MimeBodyPart file=new MimeBodyPart();
			
			try{
				text.setText(message);
				//create file for storing file object
				
				File file1=new File(path);
				file.attachFile(file1);
				
				mm.addBodyPart(text);
				mm.addBodyPart(file);
				
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			m.setContent(mm);
			
			
			//send
			
			//step 3:send the msg using transport class.
			Transport.send(m);
			System.out.println("Send Success..........");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		

	// this msg is responsible to send msg.
	private static void sendEmail(String message, String subject, String to, String from) {
		// Variable for gmail host.
		String host = "smtp.gmail.com";

		// get the system properties.

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES :" + properties);

		// setting important information to properties object

		// host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1:to get the session object.

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("chetankagda238@gmail.com", "8320401936");
			}

		});

		session.setDebug(true);

		// step 2 : compose the msg [TEXT]

		MimeMessage m = new MimeMessage(session);

		try {
			
			// from email id
			m.setFrom(from);

			// to email id
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
			//adding subject to msg
			m.setSubject(subject);
			
			//adding text to text
			m.setText(message);
			
			//send
			
			//step 3:send the msg using transport class.
			Transport.send(m);
			System.out.println("Send Success..........");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
