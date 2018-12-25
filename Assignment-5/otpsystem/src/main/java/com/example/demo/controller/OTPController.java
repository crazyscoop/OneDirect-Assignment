package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OTPSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/*
 * We are using Twilio platform to send OTP.
 */
@RestController
public class OTPController {
	
	private Map<String,OTPSystem > otpTable = new HashMap<>();
	
	/*
	 *  Twilio Account Details.
	 */
	private final static String ACCOUNT_SID = "AC647fc4ca92929d7a7d2a250e4b9de3cf";
	private final static String AUTH_ID = "0f5ad65bb98c84c25f76c19e23171063";
	
	
	/*
	 * Initializing Twilio.
	 */
	static {
		Twilio.init(ACCOUNT_SID,AUTH_ID);
	}

	
	/*
	 * Registering Mobile Number.
	 * link: localhost:8080/mobilenumber/{mob no}/otp
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/mobilenumber/{number}/otp")
	public ResponseEntity<Object> sendOTP(@PathVariable("number") String mobilenumber)
	{
		
		OTPSystem otpSystem = new OTPSystem();
		
		otpSystem.setPhoneNumber(mobilenumber);
		
		/*
		 * OTP is random 4 digit number.
		 */
		otpSystem.setOTP(String.valueOf((int)(Math.random()*9000)+1000));
		
		/*
		 * OTP expiry time is 50 sec.
		 */
		otpSystem.setExpiryTime(System.currentTimeMillis() + 50000);
		
		/*
		 * Add mobile number to the OTP table.
		 */
		otpTable.put(mobilenumber, otpSystem);
		
		/*
		 * Send OTP.
		 */
		Message message = Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+12673101594"),"Your OTP is " + otpSystem.getOTP()).create();
		
		System.out.println(message.getStatus());
		
		return new ResponseEntity<>("Your OTP is sent successfully",HttpStatus.OK);
	}
	
	
	/*
	 * Verifying OTP.
	 * link: localhost:8080/mobilenumber/{mob no}/otp
	 * Http method: PUT
	 * content-type: application/java
	 * body:
	 * 		{
	 * 			"otp" : "xxxx"
	 * 		}  
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/mobilenumber/{number}/otp")
	public ResponseEntity<Object> verifyOTP(@PathVariable("number") String mobilenumber, @RequestBody OTPSystem requestBodyOTP )
	{
		
		/*
		 * Check if body contains OTP.
		 */
		if(requestBodyOTP.getOTP() == null || requestBodyOTP.getOTP().trim().length() <= 0)
		{
			return new ResponseEntity<>("Error: Please Provide OTP.",HttpStatus.BAD_REQUEST);
		}
			
		/*
		 * Check if mobile no is registered.
		 */
		if(otpTable.containsKey(mobilenumber))
		{
			OTPSystem otpSystem = otpTable.get(mobilenumber);
			
			if(otpSystem != null)
			{
				/*
				 * Check if OTP is expired.
				 */
				if(otpSystem.getExpiryTime() > System.currentTimeMillis())
				{
					/*
					 * Check if OTP is correct.
					 */
					if(requestBodyOTP.getOTP().equals(otpSystem.getOTP()))
					{
						otpTable.remove(mobilenumber);
						
						return new ResponseEntity<>("OTP Verified Successfully.",HttpStatus.OK);
					}
					return new ResponseEntity<>("Error: Invalid OTP.",HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("Error: OTP Expired. Request For New OTP.",HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Error: OTPSystem Error !!!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Error: Mobile Number Not Found !!!",HttpStatus.NOT_FOUND);
	}
	
	
}
