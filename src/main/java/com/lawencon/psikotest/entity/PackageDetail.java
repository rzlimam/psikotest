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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_m_package_detail",
uniqueConstraints = {@UniqueConstraint (columnNames = {"package_id", "questionId"})})
public class PackageDetail {
	@Id
	@Column(name="package_question_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String packageQuestionId;
	
	@JsonIgnoreProperties(value = {"packageDetails"})
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Packages packages ;
	
	@OneToOne
	@JoinColumn(name = "questionId", nullable = false)
	private Question question ;
	
	@Column(name="is_active")
	private Boolean isActive;

	public PackageDetail() {
		super();
	}

	public String getPackageQuestionId() {
		return packageQuestionId;
	}

	public void setPackageQuestionId(String packageQuestionId) {
		this.packageQuestionId = packageQuestionId;
	}

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
