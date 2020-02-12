package com.lawencon.psikotest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "tbl_m_question",
uniqueConstraints = {@UniqueConstraint (columnNames = {"data"})})
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Question {
	
	@Id
	@Column(name="question_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String questionId;
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionTypeId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private QuestionType questionType;
	
	@Column(name="question_title")
	private String questionTitle;
	
	@Type(type = "jsonb")
	@Column(columnDefinition ="data")
	private QuestionData data;
	
	@Type(type = "jsonb")
	@Column(columnDefinition ="answer")
	private ValidAnswer answer;
	
	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="date_of_question")
	private Date dateOfQuestion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public QuestionData getData() {
		return data;
	}

	public void setData(QuestionData data) {
		this.data = data;
	}

	public ValidAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(ValidAnswer answer) {
		this.answer = answer;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDateOfQuestion() {
		return dateOfQuestion;
	}

	public void setDateOfQuestion(Date dateOfQuestion) {
		this.dateOfQuestion = dateOfQuestion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	


}
