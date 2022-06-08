package com.lawencon.master.dto.chartofaccount;

public class InsertCoADtoReq {
	private Long coaParent;
	private String coaCode;
	private String coaName;
	private Long typeId;

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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
