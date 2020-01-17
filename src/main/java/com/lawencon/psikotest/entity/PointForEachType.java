package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="tbl_point_for_each_type")
public class PointForEachType {
	@Id
	@Column(name="point_type_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String pointTypeId;
	
	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@OneToOne
	@JoinColumn(name = "questionTypeId", nullable = false)
	private QuestionType questionType;
	
	private Integer totapPointForEachType;

	
	
	public PointForEachType() {
		super();
	}

	public String getPointTypeId() {
		return pointTypeId;
	}

	public void setPointTypeId(String pointTypeId) {
		this.pointTypeId = pointTypeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Integer getTotapPointForEachType() {
		return totapPointForEachType;
	}

	public void setTotapPointForEachType(Integer totapPointForEachType) {
		this.totapPointForEachType = totapPointForEachType;
	}
	
	
}
