package com.lawencon.psikotest.entity;

import java.util.List;

public class QuestionData {
	private String question;
	private List<String> questionImage;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<String> getQuestionImage() {
		return questionImage;
	}
	public void setQuestionImage(List<String> questionImage) {
		this.questionImage = questionImage;
	}
	
	public String getChoiceA() {
		return choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	
	public String getChoiceB() {
		return choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	
	public String getChoiceC() {
		return choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	
	public String getChoiceD() {
		return choiceD;
	}
	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}
}
