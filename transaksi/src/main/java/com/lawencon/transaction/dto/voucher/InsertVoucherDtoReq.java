package com.lawencon.transaction.dto.voucher;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsertVoucherDtoReq {
	private String transactionCode;
	private Long coaId;
	private String description;
	private Double totalTransaction;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date transactionDate;

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public Long getCoaId() {
		return coaId;
	}

	public void setCoaId(Long coaId) {
		this.coaId = coaId;
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
