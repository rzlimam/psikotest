package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_question_type")
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
	
	@OneToOne
	@JoinColumn(name = "answerTypeId ", nullable = false)
	private AnswerType answerType;

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
