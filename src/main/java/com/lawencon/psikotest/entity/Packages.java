package com.lawencon.psikotest.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(name="amount_of_time")
	private Integer amountOfTime;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "packages", fetch = FetchType.LAZY)
	private List<PackageDetail> packageDetails;

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

	public Integer getAmountOfTime() {
		return amountOfTime;
	}

	public void setAmountOfTime(Integer amountOfTime) {
		this.amountOfTime = amountOfTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PackageDetail> getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(List<PackageDetail> packageDetails) {
		this.packageDetails = packageDetails;
	}

	
}
