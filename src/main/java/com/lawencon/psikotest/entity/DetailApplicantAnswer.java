package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_detail_applicant_answer")
public class DetailApplicantAnswer {
	@Id
	@Column(name="detail_answer_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String detailAnswerId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicantAnswerId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private HeaderApplicantAnswer applicantAnswerId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "packageQuestionId", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private PackageDetail packageQuestion;
	
	@Transient
	@Type(type = "jsonb")
	@Column(columnDefinition ="applicant_answer")
	private ApplicantAnswer applicatnAnswer;
	
	private Integer point;

	public DetailApplicantAnswer() {
		super();
	}

	public String getDetailAnswerId() {
		return detailAnswerId;
	}

	public void setDetailAnswerId(String detailAnswerId) {
		this.detailAnswerId = detailAnswerId;
	}

	public HeaderApplicantAnswer getApplicantAnswerId() {
		return applicantAnswerId;
	}

	public void setApplicantAnswerId(HeaderApplicantAnswer applicantAnswerId) {
		this.applicantAnswerId = applicantAnswerId;
	}

	public PackageDetail getPackageQuestion() {
		return packageQuestion;
	}

	public void setPackageQuestion(PackageDetail packageQuestion) {
		this.packageQuestion = packageQuestion;
	}

	public ApplicantAnswer getApplicatnAnswer() {
		return applicatnAnswer;
	}

	public void setApplicatnAnswer(ApplicantAnswer applicatnAnswer) {
		this.applicatnAnswer = applicatnAnswer;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	
	
}
