package com.trion.healthcare.model;

public class RestError {
	
	private String faultCode;
	
	private String faultMessage;

	public RestError(Exception e) {
		this.faultCode = e.getClass().getName();
		this.faultMessage = e.getMessage();
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
}
