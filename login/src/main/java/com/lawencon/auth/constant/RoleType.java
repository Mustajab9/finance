package com.lawencon.auth.constant;

public enum RoleType {
	ADMIN("admin", "U01"), NONADMIN("non-admin", "U02");
	
	private String detail;
	private String code;
	
	private RoleType(String detail, String code){
		this.detail = detail;
		this.code = code;
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	public String getCode() {
		return this.code;
	}
}
