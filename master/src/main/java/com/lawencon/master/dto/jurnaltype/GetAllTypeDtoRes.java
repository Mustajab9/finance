package com.lawencon.master.dto.jurnaltype;

import java.util.List;

public class GetAllTypeDtoRes {
	private String msg;
	private List<GetAllTypeDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllTypeDtoDataRes> data) {
		this.data = data;
	}
}
