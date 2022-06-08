package com.lawencon.transaction.dto.voucher;

public class GetByVoucherIdDtoRes {
	private String msg;
	private GetByVoucherIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByVoucherIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByVoucherIdDtoDataRes data) {
		this.data = data;
	}
}
