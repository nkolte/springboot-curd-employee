package com.nkolte.springboot.curd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String emailId;

}
