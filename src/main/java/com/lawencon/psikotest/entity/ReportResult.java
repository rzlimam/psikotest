package com.lawencon.psikotest.entity;

public class ReportResult {
	
	private String questionTitle;
	private String questionType;
	private int point;
	private String CandidateName;
	private String CandidateEmail;
	private String CandidatePhone;
	private String date;
	private int totalPoint;
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getCandidateName() {
		return CandidateName;
	}
	public void setCandidateName(String candidateName) {
		CandidateName = candidateName;
	}
	public String getCandidateEmail() {
		return CandidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		CandidateEmail = candidateEmail;
	}
	public String getCandidatePhone() {
		return CandidatePhone;
	}
	public void setCandidatePhone(String candidatePhone) {
		CandidatePhone = candidatePhone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	

}
