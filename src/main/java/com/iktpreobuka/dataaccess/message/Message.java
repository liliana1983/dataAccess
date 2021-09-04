package com.iktpreobuka.dataaccess.message;

public class Message {
private String filename;
private String message;
private String status;
public Message() {}
public Message(String filename, String message, String status) {
	super();
	this.filename = filename;
	this.message = message;
	this.status = status;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
