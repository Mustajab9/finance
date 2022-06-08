package com.lawencon.master.dto.chartofaccount;

import java.util.List;

public class GetAllCoADtoRes {
	private String msg;
	private List<GetAllCoADtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllCoADtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllCoADtoDataRes> data) {
		this.data = data;
	}
}
