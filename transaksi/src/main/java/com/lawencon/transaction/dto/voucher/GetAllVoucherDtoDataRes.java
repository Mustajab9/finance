package com.lawencon.transaction.dto.voucher;

import java.time.LocalDate;

public class GetAllVoucherDtoDataRes {
	private Long id;
	private String transactionCode;
	private LocalDate transactionDate;
	private Double totalTransaction;
	private String description;
	private Long coaId;
	private Long coaParent;
	private String coaCode;
	private String coaName;
	private String coaParentCode;
	private String coaParentName;
	private Integer version;
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(Double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCoaId() {
		return coaId;
	}

	public void setCoaId(Long coaId) {
		this.coaId = coaId;
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

	public String getCoaParentCode() {
		return coaParentCode;
	}

	public void setCoaParentCode(String coaParentCode) {
		this.coaParentCode = coaParentCode;
	}

	public String getCoaParentName() {
		return coaParentName;
	}

	public void setCoaParentName(String coaParentName) {
		this.coaParentName = coaParentName;
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
