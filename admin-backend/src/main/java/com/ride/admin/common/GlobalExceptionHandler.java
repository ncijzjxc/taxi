package com.ride.admin.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(Exception.class)
 public ApiResponse<String> handle(Exception e){
 String msg = e.getMessage();
 if (msg == null || msg.isEmpty()) {
   msg = "服务器内部错误";
 }
 return ApiResponse.fail(msg);
 }
}


