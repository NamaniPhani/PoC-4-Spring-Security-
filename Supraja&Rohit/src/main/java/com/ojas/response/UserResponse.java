package com.ojas.response;

public class UserResponse {
private Integer statusCode;
private String Message;
private Object data;
public Integer getStatusCode() {
	return statusCode;
}
public void setStatusCode(Integer statusCode) {
	this.statusCode = statusCode;
}
public String getMessage() {
	return Message;
}
public void setMessage(String Message) {
	this.Message = Message;
}
public Object getdata() {
	return data;
}
public void setdata(Object data) {
	this.data = data;
}

}
