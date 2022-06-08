package com.lawencon.transaction.dto.gl;

import java.util.List;

public class GetAllGLDtoRes {
	private String msg;
	private List<GetAllGLDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllGLDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllGLDtoDataRes> data) {
		this.data = data;
	}
}
