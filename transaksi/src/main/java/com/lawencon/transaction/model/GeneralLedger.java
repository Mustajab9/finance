package com.lawencon.transaction.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gl")
public class GeneralLedger extends BaseModel {
	@Column(name = "transaction_code", length = 20, nullable = false, unique = true)
	private String transactionCode;

	@Column(name = "transaction_date", nullable = false)
	private LocalDate transactionDate;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@ManyToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucherId;
	
	@Column(name = "coa_id", nullable = false)
	private Long coaId;

	@Column(name = "coa_code", length = 10,  nullable = false)
	private String coaCode;
	
	@Column(name = "coa_name", length = 100, nullable = false)
	private String coaName;

	@Column(name = "debit")
	private Double debit;

	@Column(name = "credit")
	private Double credit;
	
	@Column(name = "saldo")
	private Double saldo;

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

	public Voucher getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Voucher voucherId) {
		this.voucherId = voucherId;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
