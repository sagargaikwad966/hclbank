package com.bank.hclbank.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bank.hclbank.exception.ApplicationException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class CommunicationUtils {
	
	
	private static final Integer EXPIRE_MINS = 5;
	private LoadingCache<Long, Integer> otpCache;
	
	//Interface to send mails
	
	private JavaMailSender mailSender;
	
	@Autowired
	public CommunicationUtils(JavaMailSender mailSender) {
		this.mailSender = mailSender;
		
		otpCache = CacheBuilder.newBuilder().
				expireAfterWrite(
						EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<Long, Integer>() {
							public Integer load(Long key) {
								return 0;
							}
						});
	}
	
	
	/**
	 * Constructor to initialise the cache and store key into it for 5 minutes.
	 */
	public CommunicationUtils(){
		
	}

	/** This method will return the otp for the specific Key
	 * @param key
	 * @return OTP value
	 * @throws ApplicationException 
	 * @throws ExecutionException 
	 */
	public Integer getOtp(Long key) throws ApplicationException, ExecutionException{
		if( key == null) {
			throw new ApplicationException("key for the otp not found... Please try to generate new OTP !!!");
		}else {
			return otpCache.get(key);
		}
	}


	/** This method will generate OTP and store its key to cache
	 * @param key
	 * @return Integer random generated OTP
	 * @throws NoSuchAlgorithmException
	 */
	public Integer generateOTP(Long key) throws NoSuchAlgorithmException{
		Random rand = SecureRandom.getInstanceStrong();
		Integer otp = 100000 + rand.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}
	
	
	/** Method used to send mail.
	 * @param to
	 * @param subject
	 * @param message
	 */
	public void sendOtpViaMail(String to, String subject, String message) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setSubject(subject);
		msg.setTo(to);
		msg.setText(message);
        mailSender.send(msg);
	}
	

	 public String processValidOtp(Integer otpnum,Long payeeId) throws ApplicationException, ExecutionException {
		 final String SUCCESS = "SUCCESS";
			final String FAIL = "FAIL";
			
			int serverOtp = getOtp(payeeId);
			if(serverOtp == otpnum)
				return "SUCCESS";
			else
			{
				clearOTP(payeeId);
				return "FAIL";
			}

			
			
	 }
	 
	 public void clearOTP(Long key){ 
			otpCache.invalidate(key);
		 }

}
