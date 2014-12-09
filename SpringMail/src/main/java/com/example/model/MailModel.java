package com.example.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class MailModel {

	private String fromEmail;
	private String fromPassword;
	private String toEmail;
	private String subject;
	private String text;
	private List<MultipartFile> files;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "MailModel [fromEmail=" + fromEmail + ", fromPassword="
				+ fromPassword + ", toEmail=" + toEmail + ", subject="
				+ subject + ", text=" + text + ", files=" + files + "]";
	}

}
