package com.lawencon.psikotest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_m_package")
public class Packages {
	@Id
	@Column(name="package_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String packageId;
	
	@Column(name="package_name")
	private String packageName;

	@Temporal(TemporalType.TIME)
	private Date amountOfTime;
	
	@Column(name="is_active")
	private Boolean isActive;

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getAmountOfTime() {
		return amountOfTime;
	}

	public void setAmountOfTime(Date amountOfTime) {
		this.amountOfTime = amountOfTime;
	}
	
	
}
