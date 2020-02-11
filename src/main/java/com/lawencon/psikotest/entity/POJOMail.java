package com.lawencon.psikotest.entity;

import java.util.Map;

public class POJOMail {
	
	private String name;
	private String email;
	private String password;
	private String userId;
	private String link;
	
	private Map<String, String> mail;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Map<String, String> getMail() {
		return mail;
	}
	public void setMail(Map<String, String> mail) {
		this.mail = mail;
	}
	
	

}
