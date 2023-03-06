package com.example.demo.custom.annotation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizeUserAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(AuthorizeUserAspect.class);


    @Around("@annotation(AuthorizeUser)")
    public ResponseEntity<?> authorize(ProceedingJoinPoint joinPoint) throws Throwable {

    	//BEFORE METHOD EXECUTION
        Long user_id = (Long) joinPoint.getArgs()[0];
        LOGGER.info(" Check for user access ");
		LOGGER.info(" Allowed Access for {}", +user_id);

        //Only user id 1 is authorize to login, other user are not valid users.
        if (user_id != 1) {
            //write authorization verification business logic
        	LOGGER.info("Invalid User : " + user_id);
    		return ResponseEntity.status(HttpStatus.OK).body(user_id + " is Invalid User. Please login with correct credential.");
        }

		//This is where ACTUAL METHOD will get invoke
        ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
       

      
        return  ResponseEntity.status(HttpStatus.OK).body(result.getBody());
    }
}
