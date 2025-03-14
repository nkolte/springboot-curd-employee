package com.nkolte.springboot.curd.service;

import com.nkolte.springboot.curd.dto.EmployeeDTO;
import com.nkolte.springboot.curd.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO findEmployeeById(long employeeId);
    Optional<EmployeeDTO> findEmployeeByEmailId(String emailId);
    List<EmployeeDTO> findAllEmployees();
    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);
    EmployeeDTO patchEmployee(Long employeeId, Map<String, Object> fields);
    String deleteEmployee(Long employeeId);
}
