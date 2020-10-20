package com.poc.response;

public class Response {
private String message;
private String statusCode;
private Object empList;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public Object getEmpList() {
	return empList;
}
public void setEmpList(Object empList) {
	this.empList = empList;
}
@Override
public String toString() {
	return "Response [message=" + message + ", statusCode=" + statusCode + ", empList=" + empList + "]";
}
}
