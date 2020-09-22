package model;

import java.sql.Timestamp;

public class MessageDto {
	private String messageId;
	private String userName;
	private String message;
	private Timestamp time;
	private byte[] imageByte;

	public String getMessageId() {return messageId;}
	public void setMessageId(String messageId) {this.messageId = messageId;}

	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}

	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}

	public Timestamp getTime() {return time;}
	public void setTime(Timestamp time) {this.time = time;}

	public byte[] getByte() {return imageByte;}
	public void setByte(byte[] imageByte) {this.imageByte = imageByte;}
}
