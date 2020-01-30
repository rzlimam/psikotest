package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_tr_question_assign")
public class QuestionAssign {
	
	@Id
	@Column(name="assign_question_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String AssignQuestionId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;
	
	@JsonIgnoreProperties(value = {"packageDetails"})
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "packageId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Packages packagee;

	public String getAssignQuestionId() {
		return AssignQuestionId;
	}

	public void setAssignQuestionId(String assignQuestionId) {
		AssignQuestionId = assignQuestionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Packages getPackagee() {
		return packagee;
	}

	public void setPackagee(Packages packagee) {
		this.packagee = packagee;
	}

	
}
