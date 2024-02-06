package Email;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    public void sendEmail(String to,String subject,String body) throws MessagingException {
        String host="smtp.gmail.com";
        String username="pandukapiushanpullaperuma4321@gmail.com";
        String password="wpod tfbd hwrn bfia";
        int port=587;

        Properties props=new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
        System.out.println("Email sent successfully to " + to);
    }

    public String sendOtp(String to){
        String otp = generateOTP();
        String body=String.format("Your OTP: %s",otp);
        try {
            sendEmail(to,"Your OTP Message",body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return otp;
    }

    private String generateOTP() {
        Random random = new Random();
        int number=3000+random.nextInt(5000+10);
        return String.valueOf(number);

    }
}
