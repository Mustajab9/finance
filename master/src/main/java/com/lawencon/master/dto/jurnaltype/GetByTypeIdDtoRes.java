package com.lawencon.master.dto.jurnaltype;

public class GetByTypeIdDtoRes {
	private String msg;
	private GetByTypeIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public GetByTypeIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByTypeIdDtoDataRes data) {
		this.data = data;
	}
}
