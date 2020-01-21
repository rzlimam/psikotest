package com.lawencon.psikotest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="tbl_header_applicant_answer")
public class HeaderApplicantAnswer {
	@Id
	@Column(name="applicant_answer_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String applicantAnswerId;
	
	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@Column(name="date_of_answer")
	private Date dateOfAnswer;
	
	@Column(name="total_point")
	private Integer totalPoint;
	
	@Column(name="total_question")
	private Integer totalQuestion;
	
	@Column(name="status")
	private String status;

	public String getApplicantAnswerId() {
		return applicantAnswerId;
	}

	public void setApplicantAnswerId(String applicantAnswerId) {
		this.applicantAnswerId = applicantAnswerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateOfAnswer() {
		return dateOfAnswer;
	}

	public void setDateOfAnswer(Date dateOfAnswer) {
		this.dateOfAnswer = dateOfAnswer;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public HeaderApplicantAnswer() {
		super();
	}

	public Integer getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
