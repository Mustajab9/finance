package com.lawencon.transaction.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "voucher")
public class Voucher extends BaseModel {
	@Column(name = "transaction_code", length = 20, nullable = false, unique = true)
	private String transactionCode;

	@Column(name = "transaction_date", nullable = false)
	private LocalDate transactionDate;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "total_transaction", nullable = false)
	private Double totalTransaction;
	
	@Column(name = "coa_id", nullable = false)
	private Long coaId;

	@Column(name = "coa_code", length = 10,  nullable = false)
	private String coaCode;
	
	@Column(name = "coa_name", length = 100, nullable = false)
	private String coaName;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(Double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public Long getCoaId() {
		return coaId;
	}

	public void setCoaId(Long coaId) {
		this.coaId = coaId;
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
}
