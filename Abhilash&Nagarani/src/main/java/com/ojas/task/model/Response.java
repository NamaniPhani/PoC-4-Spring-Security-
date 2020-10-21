package com.ojas.task.model;

import org.springframework.http.HttpStatus;

public class Response {

	private Object object;
	private String message;
	private HttpStatus status;

	public Response(Object object, String message, HttpStatus status) {
		super();
		this.object = object;
		this.message = message;
		this.status = status;
	}
	public final Object getObject() {
		return object;
	}

	public final void setObject(Object object) {
		this.object = object;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}

	public final HttpStatus getStatus() {
		return status;
	}

	public final void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Response() {
		super();
	}


	@Override
	public String toString() {
		return "Response [object=" + object + ", message=" + message + ", status=" + status + "]";
	}

}
