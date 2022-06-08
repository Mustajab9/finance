package com.lawencon.transaction.dto.gl;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsertGLDtoReq {
	private Long coaId;
	private String transactionCode;
	private Double debit;
	private Double credit;
	private String description;
	private Long voucherId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date transactionDate;

	public Long getCoaId() {
		return coaId;
	}

	public void setCoaId(Long coaId) {
		this.coaId = coaId;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
