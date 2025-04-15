package com.springsecurity.springsecurity.exception;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger  LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMalformedJwtException(MalformedJwtException ex, HttpServletRequest httpServletRequest) {
//        LOGGER.info("handleMalformedJwtException");
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI());
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleSignatureException(SignatureException ex, HttpServletRequest httpServletRequest) {
//        LOGGER.info("handleMalformedJwtException");
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI());
    }
}
