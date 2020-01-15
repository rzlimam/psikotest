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
@Table(name="tbl_m_package_detail")
public class PackageDetail {
	@Id
	@Column(name="package_question_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String packageQuestionId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "packageId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Packages packageId ;
	
	@OneToOne
	@JoinColumn(name = "qeustionId", nullable = false)
	private Question questionId ;
	

	public PackageDetail() {
		super();
	}

	public String getPackageQuestionId() {
		return packageQuestionId;
	}

	public void setPackageQuestionId(String packageQuestionId) {
		this.packageQuestionId = packageQuestionId;
	}

	public Packages getPackageId() {
		return packageId;
	}

	public void setPackageId(Packages packageId) {
		this.packageId = packageId;
	}

	public Question getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}

	
	
	
	
}
