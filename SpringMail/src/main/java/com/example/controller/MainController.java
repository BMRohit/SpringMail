package com.example.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.MailModel;

@Controller
public class MainController {

	private JavaMailSender mailSender;
	@RequestMapping(value = "/")
	public ModelAndView mainPage() {
		return new ModelAndView("sendmail");
	}

	@RequestMapping(value = "/index")
	public ModelAndView indexPage() {
		return new ModelAndView("sendmail");
	}

	@RequestMapping(value = "/sendmailaction", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute("mailDetails") MailModel mailDetails) {
		if(mailDetails != null)
		{
			sendMail(mailDetails);
			return new ModelAndView("success").addObject("message", "Mail sent to "
					+ mailDetails.getToEmail());
		}
		else
		{
			return new ModelAndView("success").addObject("message", "Failed to send mail to ");
		}
	}

	public void sendMail(MailModel mailDetails) {
		mailSender = getMailSender(mailDetails);
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailDetails.getFromEmail());
			helper.setTo(mailDetails.getToEmail());
			helper.setSubject(mailDetails.getSubject());
			helper.setText(mailDetails.getText());
			if (mailDetails.getFiles() != null) {
				if (mailDetails.getFiles().size() > 0) {
					for(MultipartFile file : mailDetails.getFiles())
					{
						byte [] byteArr=file.getBytes();
						InputStream inputStream = new ByteArrayInputStream(byteArr);
						   helper.addAttachment(file.getOriginalFilename(),
								    new ByteArrayResource(IOUtils.toByteArray(inputStream)));
					}
				}
			}
			mailSender.send(message);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JavaMailSenderImpl getMailSender(MailModel mailDetails) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(mailDetails.getFromEmail());
		mailSender.setPassword(mailDetails.getFromPassword());
		Properties props = new Properties();
		  props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "25");
		mailSender.setJavaMailProperties(props);
		return mailSender;

	}
}
