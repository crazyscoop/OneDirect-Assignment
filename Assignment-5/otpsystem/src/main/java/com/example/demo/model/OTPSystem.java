package com.example.demo.model;

/*
 * Stores OTP details.
 */
public class OTPSystem {
	
	private String phoneNumber;
	private String OTP;
	private long expiryTime;
	
	public OTPSystem()
	{
		
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getOTP() {
		return OTP;
	}
	
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	
	public long getExpiryTime() {
		return expiryTime;
	}
	
	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}

}
