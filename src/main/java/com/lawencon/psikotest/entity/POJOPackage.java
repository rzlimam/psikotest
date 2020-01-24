package com.lawencon.psikotest.entity;

public class POJOPackage {
	
	private String packageId;
	private String packageName;
	private String amountOfTime;
	private Integer totalQuestion;
	private String questionType;
	private String description;
	
	
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getAmountOfTime() {
		return amountOfTime;
	}
	public void setAmountOfTime(String amountOfTime) {
		this.amountOfTime = amountOfTime;
	}
	public Integer getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
