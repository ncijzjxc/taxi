package com.ride.admin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
 private int code;
 private String message;
 private T data;

 public static <T> ApiResponse<T> ok(T data){
 return new ApiResponse<>(0, "ok", data);
 }

 public static <T> ApiResponse<T> fail(String msg){
 return new ApiResponse<>(-1, msg, null);
 }
}

