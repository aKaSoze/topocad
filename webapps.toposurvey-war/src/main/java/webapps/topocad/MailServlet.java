package webapps.topocad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Acest mail a fost trimis prin app engine, imi pare rau ca am uitat sa cobor cu cheia, m-am gandit ca mai bine imi ofer un motiv sa-ti trimit acest mail rezolvand problema trimiteri lui.";

		// Set response content type
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("mrsoze.ks@gmail.com",
					"App Engine"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"corina_geboiu@yahoo.com", "Yo coco"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"nica_petre_sorin@yahoo.com", "Mr. Soze"));
			msg.setSubject("Scuze papusa");
			msg.setText(msgBody);
			Transport.send(msg);
			out.println("<h1>Email Sent Neg</h1>");
		} catch (MessagingException e) {
			out.println("<h1>Email Was Not Sent Neg</h1>");
			out.println("<h1>" + e.getMessage() + "</h1>");
		}
	}

}
