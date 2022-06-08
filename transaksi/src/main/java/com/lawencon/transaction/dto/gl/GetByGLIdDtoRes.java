package com.lawencon.transaction.dto.gl;

public class GetByGLIdDtoRes {
	private String msg;
	private GetByGLIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByGLIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByGLIdDtoDataRes data) {
		this.data = data;
	}
}
