package com.team2.wtw.sanction;

import java.sql.Timestamp;

public class SanctionMemberData {
		
		
	    public int memberNo;
	    public String memberId;
	    public String sancType;
	    public String sancCatName;
	    public Timestamp sanctionDate;
	    public Timestamp expirationDate;
	    public int adminNo;

	    public int getMemberNo() {
	        return memberNo;
	    }

	    public void setMemberNo(int memberNo) {
	        this.memberNo = memberNo;
	    }

	    public String getMemberId() {
	        return memberId;
	    }

	    public void setMemberId(String memberId) {
	        this.memberId = memberId;
	    }

	    public String getSancType() {
	        return sancType;
	    }

	    public void setSancType(String sancType) {
	        this.sancType = sancType;
	    }

	    public String getSancCatName() {
	        return sancCatName;
	    }

	    public void setSancCatName(String sancCatName) {
	        this.sancCatName = sancCatName;
	    }

	    public Timestamp getSanctionDate() {
	        return sanctionDate;
	    }

	    public void setSanctionDate(Timestamp sanctionDate) {
	        this.sanctionDate = sanctionDate;
	    }

	    public Timestamp getExpirationDate() {
	        return expirationDate;
	    }

	    public void setExpirationDate(Timestamp expirationDate) {
	        this.expirationDate = expirationDate;
	    }

	    public int getAdminNo() {
	        return adminNo;
	    }

	    public void setAdminNo(int adminNo) {
	        this.adminNo = adminNo;
	    }
	


}
