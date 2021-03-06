package com.lawencon.psikotest.entity;

import java.util.Date;

public class POJODashboard {
	
	private Integer totalPass;
	private Integer totalFail;
	private Integer totalQuestion;
	private Integer totalPackage;
	private String profileName;
	private Integer totalPoint;
	private Double score;
	private Date dateOfAnswer;
	private String appliedPosition;
	private String status;
	
	public Integer getTotalPass() {
		return totalPass;
	}
	public void setTotalPass(Integer totalPass) {
		this.totalPass = totalPass;
	}
	public Integer getTotalFail() {
		return totalFail;
	}
	public void setTotalFail(Integer totalFail) {
		this.totalFail = totalFail;
	}
	public Integer getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public Integer getTotalPackage() {
		return totalPackage;
	}
	public void setTotalPackage(Integer totalPackage) {
		this.totalPackage = totalPackage;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Date getDateOfAnswer() {
		return dateOfAnswer;
	}
	public void setDateOfAnswer(Date dateOfAnswer) {
		this.dateOfAnswer = dateOfAnswer;
	}
	public String getAppliedPosition() {
		return appliedPosition;
	}
	public void setAppliedPosition(String appliedPosition) {
		this.appliedPosition = appliedPosition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
