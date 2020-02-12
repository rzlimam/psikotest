package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="tbl_question_type",
uniqueConstraints = {@UniqueConstraint (columnNames = {"questionTypeTitle"})})
public class QuestionType {
	
	@Id
	@Column(name="question_type_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String questionTypeId;
	
	@Column(name="question_type_title")
	private String questionTypeTitle;
	
	@Column(name="amount_of_answer")
	private Integer amountOfAnswer;
	
	@Column(name="is_active")
	private Boolean isActive;
	

	public String getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(String questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getQuestionTypeTitle() {
		return questionTypeTitle;
	}

	public void setQuestionTypeTitle(String questionTypeTitle) {
		this.questionTypeTitle = questionTypeTitle;
	}	

	public Integer getAmountOfAnswer() {
		return amountOfAnswer;
	}

	public void setAmountOfAnswer(Integer amountOfAnswer) {
		this.amountOfAnswer = amountOfAnswer;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
