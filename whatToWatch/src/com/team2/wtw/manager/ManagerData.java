package com.team2.wtw.manager;

public class ManagerData {
	private int adminNo;
	private int permissionNo;
	private String adminId;
	private String adminPwd;
	private String adminName;
	
	@Override
	public String toString() {
		return "ManagerData [adminNo=" + adminNo + ", permissionNo=" + permissionNo + ", adminId=" + adminId
				+ ", adminPwd=" + adminPwd + ", adminName=" + adminName + "]";
	}
	
	public int getAdminNo() {
		return adminNo;
	}
	
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	
	public int getPermissionNo() {
		return permissionNo;
	}
	
	public void setPermissionNo(int permissionNo) {
		this.permissionNo = permissionNo;
	}
	
	public String getAdminId() {
		return adminId;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public String getAdminPwd() {
		return adminPwd;
	}
	
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
