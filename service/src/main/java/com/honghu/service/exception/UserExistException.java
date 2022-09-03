package com.honghu.service.exception;

public class UserExistException extends RuntimeException {
    public UserExistException() {
        super("该用户名已存在");
    }
}