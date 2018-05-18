package com.example.demo.aspect;


import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class LoggingAspect {

    /*Write loggers for each of the methods of service,
    any particular method will have all the four aspectJ annotation
    (@Before, @After, @AfterReturning, @AfterThrowing).
   */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.example.demo.controller.*.*(..)) ")
    public void logBeforeValidateUser(JoinPoint joinPoint) {
 System.err.println("fgdf");
        logger.info("============@Before==========");
        logger.debug("Method Name : " + joinPoint.getSignature().getName());
        logger.debug("*********************************");

    }

    @After("execution(* com.example.demo.controller.*.*(..)) ")
    public void logAfterValidateUser(JoinPoint joinPoint) {

        logger.info("============@After==========");
        logger.debug("Method Name : " + joinPoint.getSignature().getName());
        logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
        logger.debug("*********************************");

    }

    @AfterReturning(pointcut = "execution(* com.example.demo.controller.*.*(..)) ", returning = "result")
    public void logAfterReturningValidateUser(JoinPoint joinPoint, Object result) {

        logger.debug("============@AfterReturning==========");
        logger.debug("Method Name : " + joinPoint.getSignature().getName());
        logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
        logger.debug("*********************************");

    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.controller.*.*(..)) ", throwing = "error")
    public void logAfterThrowingValidateUser(JoinPoint joinPoint, Throwable error) {

        logger.info("============@AfterThrowing==========");
        logger.debug("Method Name : " + joinPoint.getSignature().getName());
        logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
        logger.debug("Exception : " + error);
        logger.debug("*********************************");
    }
    
}