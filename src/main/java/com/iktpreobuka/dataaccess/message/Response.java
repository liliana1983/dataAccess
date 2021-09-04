package com.iktpreobuka.dataaccess.message;

import java.util.List;

public class Response {
private List<Message> messages=null;
private List<FileInfo> fileInfos= null;
private Error error=null;
private String errorStatus="";
public Response(List<Message> messages, List<FileInfo> fileInfos, Error error, String errorStatus) {
	super();
	this.messages = messages;
	this.fileInfos = fileInfos;
	this.error = error;
	this.errorStatus = errorStatus;
}
public Response () {}
public List<Message> getMessages() {
	return messages;
}
public void setMessages(List<Message> messages) {
	this.messages = messages;
}
public List<FileInfo> getFileInfos() {
	return fileInfos;
}
public void setFileInfos(List<FileInfo> fileInfos) {
	this.fileInfos = fileInfos;
}
public Error getError() {
	return error;
}
public void setError(Error error) {
	this.error = error;
}
public String getErrorStatus() {
	return errorStatus;
}
public void setErrorStatus(String errorStatus) {
	this.errorStatus = errorStatus;
}


}
