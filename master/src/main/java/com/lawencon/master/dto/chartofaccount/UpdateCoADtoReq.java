package com.lawencon.master.dto.chartofaccount;

public class UpdateCoADtoReq {
	private Long Id;
	private Long coaParent;
	private String coaCode;
	private String coaName;
	private Long typeId;
	private Integer version;
	private Boolean isActive;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
