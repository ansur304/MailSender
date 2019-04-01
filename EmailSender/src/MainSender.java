import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MainSender {
	
	// DO NOT FORGET to add javamail API jars to project Build path
	// turn off "Less secure app access" to ON in gmail/yahoo
	public static void main(String args[]){
		
		String userName ="ansur304";
		String password="@nsari985";
		
		String fromEmail ="ansur304@yahoo.com";
		String toEmail1 ="ansur985@gmail.com";
		String toEmail2 ="ansur304@yahoo.com";
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		//prop.setProperty("mail.smtp.host", "smtp.gmail.com"); // set fromEmail, toEmail accordingly
		prop.setProperty("mail.smtp.host", "smtp.mail.yahoo.com");
		prop.setProperty("mail.smtp.port", "587");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication (){
				return new PasswordAuthentication(userName, password);
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail1));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail2));
			msg.setSubject("Test Message with attachment .txt");			
			
			Multipart emailContent = new MimeMultipart();
			
			MimeBodyPart bodyText = new MimeBodyPart();
			bodyText.setText("Dummy with attachment");
			
			MimeBodyPart attachments = new MimeBodyPart();
			try {
				attachments.attachFile("C:\\DevStuff\\OT Hours.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			emailContent.addBodyPart(bodyText);
			emailContent.addBodyPart(attachments);
			
			msg.setContent(emailContent);
			Transport.send(msg);
			System.out.println("Sent Successfully.");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
