package com.lawencon.psikotest.entity;

public class POJOStats {
	
	private String packageName;
	private String question;
	private Integer correct;
	private Integer totalQuestion;
	private Double percentage;
	
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getCorrect() {
		return correct;
	}
	public void setCorrect(Integer correct) {
		this.correct = correct;
	}
	public Integer getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	

}
