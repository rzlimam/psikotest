package com.lawencon.psikotest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_m_role",
uniqueConstraints = {@UniqueConstraint (columnNames = {"code_role"})})
public class Role {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String roleId;
	
	@Column(name="code_role")
	private String codeRole;
	
	@Column(name="nama_role")
	private String namaRole;
	
	@Column(name="is_active")
	private Boolean isActive;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}

	public String getNamaRole() {
		return namaRole;
	}

	public void setNamaRole(String namaRole) {
		this.namaRole = namaRole;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
