package signup;

import java.sql.Date;

public class ClientDTO {
	
	private String clientId;
	private String clientHash;
	private String clientName;
	private String clientEmail;
	private String clientTel;
	private String clientBirth;
	private String clientIp;
	private String clientCheck;
	private Date clientStartDate;
	private String clientDeleteAccount;
	private Date clientLastDate;
	private String deliveryPost;
	private String deliveryAddr;
	public ClientDTO() {
		super();
	}
	public ClientDTO(String clientId, String clientHash, String clientName, String clientEmail, String clientTel,
			String clientBirth, String clientIp, String clientCheck, Date clientStartDate, String clientDeleteAccount,
			Date clientLastDate, String deliveryPost, String deliveryAddr) {
		super();
		this.clientId = clientId;
		this.clientHash = clientHash;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientTel = clientTel;
		this.clientBirth = clientBirth;
		this.clientIp = clientIp;
		this.clientCheck = clientCheck;
		this.clientStartDate = clientStartDate;
		this.clientDeleteAccount = clientDeleteAccount;
		this.clientLastDate = clientLastDate;
		this.deliveryPost = deliveryPost;
		this.deliveryAddr = deliveryAddr;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientHash() {
		return clientHash;
	}
	public void setClientHash(String clientHash) {
		this.clientHash = clientHash;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	public String getClientBirth() {
		return clientBirth;
	}
	public void setClientBirth(String clientBirth) {
		this.clientBirth = clientBirth;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientCheck() {
		return clientCheck;
	}
	public void setClientCheck(String clientCheck) {
		this.clientCheck = clientCheck;
	}
	public Date getClientStartDate() {
		return clientStartDate;
	}
	public void setClientStartDate(Date clientStartDate) {
		this.clientStartDate = clientStartDate;
	}
	public String getClientDeleteAccount() {
		return clientDeleteAccount;
	}
	public void setClientDeleteAccount(String clientDeleteAccount) {
		this.clientDeleteAccount = clientDeleteAccount;
	}
	public Date getClientLastDate() {
		return clientLastDate;
	}
	public void setClientLastDate(Date clientLastDate) {
		this.clientLastDate = clientLastDate;
	}
	public String getDeliveryPost() {
		return deliveryPost;
	}
	public void setDeliveryPost(String deliveryPost) {
		this.deliveryPost = deliveryPost;
	}
	public String getDeliveryAddr() {
		return deliveryAddr;
	}
	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}
	@Override
	public String toString() {
		return "ClientDTO [clientId=" + clientId + ", clientHash=" + clientHash + ", clientName=" + clientName
				+ ", clientEmail=" + clientEmail + ", clientTel=" + clientTel + ", clientBirth=" + clientBirth
				+ ", clientIp=" + clientIp + ", clientCheck=" + clientCheck + ", clientStartDate=" + clientStartDate
				+ ", clientDeleteAccount=" + clientDeleteAccount + ", clientLastDate=" + clientLastDate
				+ ", deliveryPost=" + deliveryPost + ", deliveryAddr=" + deliveryAddr + "]";
	}

	
}
