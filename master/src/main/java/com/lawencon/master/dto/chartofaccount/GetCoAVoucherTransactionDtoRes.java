package com.lawencon.master.dto.chartofaccount;

import java.util.List;

public class GetCoAVoucherTransactionDtoRes {
	private String msg;
	private List<GetCoAVoucherTransactionDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetCoAVoucherTransactionDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetCoAVoucherTransactionDtoDataRes> data) {
		this.data = data;
	}
}
