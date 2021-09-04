package com.iktpreobuka.dataaccess.message;

public class Error {
private String errCode;
private String errDesc;
public String getErrCode() {
	return errCode;
}
public void setErrCode(String errCode) {
	this.errCode = errCode;
}
public String getErrDesc() {
	return errDesc;
}
public void setErrDesc(String errDesc) {
	this.errDesc = errDesc;
}
public Error(String errCode, String errDesc) {
	super();
	this.errCode = errCode;
	this.errDesc = errDesc;
}
public Error() {
	super();
	// TODO Auto-generated constructor stub
}
}
