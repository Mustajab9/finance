package com.lawencon.auth.dto.role;

import java.util.List;

public class GetAllRoleDtoRes {
	private String msg;
	private List<GetAllRoleDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllRoleDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllRoleDtoDataRes> data) {
		this.data = data;
	}
}
