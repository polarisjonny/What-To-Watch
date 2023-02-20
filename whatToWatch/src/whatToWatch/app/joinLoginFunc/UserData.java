package whatToWatch.app.joinLoginFunc;

public class UserData {
	private int userNum;
	private String userId;
	private String userPwd;
	private String userNick;
	private String userEmail;
	private String userPhoneNumber;
	private String userSecurityQ;
	private String userSecurityA;
	private String tempSecurityA;
	
	
	

	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", userPwd=" + userPwd + ", userNick=" + userNick + ", userEmail="
				+ userEmail + ", userPhoneNumber=" + userPhoneNumber + ", userSecurityQ=" + userSecurityQ
				+ ", userSecurityA=" + userSecurityA + ", tempSecurityA=" + tempSecurityA + "]";
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserNick() {
		return userNick;
	}


	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}


	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}


	public String getUserSecurityQ() {
		return userSecurityQ;
	}


	public void setUserSecurityQ(String userSecurityQ) {
		this.userSecurityQ = userSecurityQ;
	}


	public String getUserSecurityA() {
		return userSecurityA;
	}


	public void setUserSecurityA(String userSecurityA) {
		this.userSecurityA = userSecurityA;
	}


	public String getTempSecurityA() {
		return tempSecurityA;
	}


	public void setTempSecurityA(String tempSecurityA) {
		this.tempSecurityA = tempSecurityA;
	}


	public int getUserNum() {
		return userNum;
	}


	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
}
