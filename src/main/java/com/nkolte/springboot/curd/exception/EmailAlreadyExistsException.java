package com.nkolte.springboot.curd.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String emaiId){
        super(String.format("Email ID %s already exists in DB, please use different email",emaiId));
    }
}
