package tdeuctests;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class OutlookOTP {

	
	public static void main(String[] args) throws MessagingException, IOException {
		String hostName = "outlook.office365.com";
		String username = "sandeep.kumar02@nagarro.com";
		String password = "Vict0ry$1234";
		int messageCount;
		int unreadMsgCount;
		String emailSubject;
		Message emailMessage;
		Properties sysProps = System.getProperties();
	    sysProps.setProperty("mail.store.protocol", "imaps");
	    Session session = Session.getInstance(sysProps, null);
        Store store = session.getStore();
        store.connect(hostName, username, password);
        Folder emailInbox = store.getFolder("Inbox");
        emailInbox.open(Folder.READ_WRITE);
        messageCount = emailInbox.getMessageCount();
        System.out.println("Total Message Count: " + messageCount);
        
        unreadMsgCount = emailInbox.getNewMessageCount();
        System.out.println("Unread Emails count:" + unreadMsgCount);
        emailMessage = emailInbox.getMessage(messageCount);
        emailSubject = emailMessage.getSubject();
        
        Pattern linkPattern = Pattern.compile("href=\"(.*)\"Jenkins Session1"); // here you need to define regex as per you need
        Matcher pageMatcher =
                linkPattern.matcher(emailMessage.getContent().toString());

        while (pageMatcher.find()) {
            System.out.println("Found OTP " + pageMatcher.group(1));
        }
        emailMessage.setFlag(Flags.Flag.SEEN, true);
        emailInbox.close(true);
        store.close();

		
	}

}
