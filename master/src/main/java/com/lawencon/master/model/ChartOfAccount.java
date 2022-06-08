package com.lawencon.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coa")
public class ChartOfAccount extends BaseModel {
	@Column(name = "coa_parent")
	private Long coaParent;
	
	@Column(name = "coa_code", length = 10, nullable = false, unique = true)
	private String coaCode;
	
	@Column(name = "coa_name", length = 100, nullable = false)
	private String coaName;
	
	@ManyToOne
	@JoinColumn(name = "jurnal_type", nullable = false)
	private JurnalType typeId;

	public Long getCoaParent() {
		return coaParent;
	}

	public void setCoaParent(Long coaParent) {
		this.coaParent = coaParent;
	}

	public String getCoaCode() {
		return coaCode;
	}

	public void setCoaCode(String coaCode) {
		this.coaCode = coaCode;
	}

	public String getCoaName() {
		return coaName;
	}

	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}

	public JurnalType getTypeId() {
		return typeId;
	}

	public void setTypeId(JurnalType typeId) {
		this.typeId = typeId;
	}
}
