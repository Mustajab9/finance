package com.lawencon.transaction.dto.voucher;

import java.util.List;

public class GetAllVoucherDtoRes {
	private String msg;
	private List<GetAllVoucherDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllVoucherDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllVoucherDtoDataRes> data) {
		this.data = data;
	}
}
