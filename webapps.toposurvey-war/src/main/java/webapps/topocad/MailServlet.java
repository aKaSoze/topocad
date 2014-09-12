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

		// Set response content type
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		try {
			String name = req.getParameter("name");
			String subject = req.getParameter("subject");
			String message = req.getParameter("message");

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("mrsoze.ks@gmail.com", name));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"corina_geboiu@yahoo.com", "Yo coco"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"nica_petre_sorin@yahoo.com", "Mr. Soze"));
			msg.setSubject(name + " - " + subject);
			msg.setText(message);
			Transport.send(msg);
			out.println("<h1>" + name + " - " + subject + "<br>" + message
					+ "</h1>");
		} catch (MessagingException e) {
			out.println("<h1>Email Was Not Sent Neg</h1>");
			out.println("<h1>" + e.getMessage() + "</h1>");
		}
	}

}
