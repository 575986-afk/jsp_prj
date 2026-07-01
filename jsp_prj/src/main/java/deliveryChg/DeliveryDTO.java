package deliveryChg;

import java.util.Date;

public class DeliveryDTO {
	
	private String deliveryID;
	private String deliveryPost;
	private String deliveryAddr;
	private String clientName;
	private String clientTel;
	private String deliveryName;
	private String deliveryTel;
	private boolean firstDestination;
	private Date inputDate;
	private String clientNo;
	public DeliveryDTO() {
		super();
	}
	public DeliveryDTO(String deliveryID, String deliveryPost, String deliveryAddr, String clientName, String clientTel,
			String deliveryName, String deliveryTel, boolean firstDestination, Date inputDate, String clientNo) {
		super();
		this.deliveryID = deliveryID;
		this.deliveryPost = deliveryPost;
		this.deliveryAddr = deliveryAddr;
		this.clientName = clientName;
		this.clientTel = clientTel;
		this.deliveryName = deliveryName;
		this.deliveryTel = deliveryTel;
		this.firstDestination = firstDestination;
		this.inputDate = inputDate;
		this.clientNo = clientNo;
	}
	public String getDeliveryID() {
		return deliveryID;
	}
	public void setDeliveryID(String deliveryID) {
		this.deliveryID = deliveryID;
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public boolean isFirstDestination() {
		return firstDestination;
	}
	public void setFirstDestination(boolean firstDestination) {
		this.firstDestination = firstDestination;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getClientNo() {
		return clientNo;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	@Override
	public String toString() {
		return "DeliveryDTO [deliveryID=" + deliveryID + ", deliveryPost=" + deliveryPost + ", deliveryAddr="
				+ deliveryAddr + ", clientName=" + clientName + ", clientTel=" + clientTel + ", deliveryName="
				+ deliveryName + ", deliveryTel=" + deliveryTel + ", firstDestination=" + firstDestination
				+ ", inputDate=" + inputDate + ", clientNo=" + clientNo + "]";
	}
	
}
