package com.nkolte.springboot.curd.exception;

public class EmployeeNotFoundException extends RuntimeException{

    private Long id;

    public EmployeeNotFoundException(Long id){
        super(String.format("Employee with id:%s not found",id));
        this.id = id;
    }
}
