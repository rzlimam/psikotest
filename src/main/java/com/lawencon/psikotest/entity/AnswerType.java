package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_answer_type")
public class AnswerType {
	@Id
	@Column(name="answer_type_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String answerTypeId;
	
	private String codeAnswerType;
	 
	private String typeOfAnswer;
	
	private String amountOfAnswer;
	
	
	
	public AnswerType() {
		super();
	}

	public String getAnswerTypeId() {
		return answerTypeId;
	}

	public void setAnswerTypeId(String answerTypeId) {
		this.answerTypeId = answerTypeId;
	}

	public String getCodeAnswerType() {
		return codeAnswerType;
	}

	public void setCodeAnswerType(String codeAnswerType) {
		this.codeAnswerType = codeAnswerType;
	}

	public String getTypeOfAnswer() {
		return typeOfAnswer;
	}

	public void setTypeOfAnswer(String typeOfAnswer) {
		this.typeOfAnswer = typeOfAnswer;
	}

	public String getAmountOfAnswer() {
		return amountOfAnswer;
	}

	public void setAmountOfAnswer(String amountOfAnswer) {
		this.amountOfAnswer = amountOfAnswer;
	}
	
	
}
