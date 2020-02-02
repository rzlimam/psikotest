package com.lawencon.psikotest.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lawencon.psikotest.entity.POJOMail;
import com.lawencon.psikotest.entity.User;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		
	}
	
	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;
	
	public void sendMail(String email, String password) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Username and Password");
		mail.setText("Username : " + email + "\n" +
				"Password : " + password);	
		javaMailSender.send(mail);
	}
	
	public void sendAccount(POJOMail mail) throws MessagingException, IOException, TemplateException {
		Map model = new HashMap();
		model.put("name", mail.getName());
        model.put("email", mail.getEmail());
        model.put("password", mail.getPassword());
        /**
         * Add below line if you need to create a token to verification emails and uncomment line:32 in "email.ftl"
         * model.put("token",UUID.randomUUID().toString());
         */

        mail.setMail(model);
        
        //log.info("Sending Email to: " + mailModel.getTo());


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/lwcn-logo.jpeg"));

        
        Template template = emailConfig.getTemplate("account.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getMail());

        mimeMessageHelper.setTo(mail.getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Linov Psikotest Account");
        mimeMessageHelper.setFrom("no-reply@gmail.com");


        javaMailSender.send(message);
		
	}

}
