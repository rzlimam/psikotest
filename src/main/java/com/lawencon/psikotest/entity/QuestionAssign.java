package com.lawencon.psikotest.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_tr_question_assign",
uniqueConstraints = {@UniqueConstraint (columnNames = {"userId", "packageId"})})
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
	
//	@JsonIgnoreProperties(value = {"packageDetails"})
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "packageId", referencedColumnName = "package_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Packages packages;
	
	@Column(name="flag")
	private Boolean flag;

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

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
}
