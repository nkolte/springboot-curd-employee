package com.nkolte.springboot.curd.controller;

import com.nkolte.springboot.curd.dto.EmployeeDTO;
import com.nkolte.springboot.curd.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(createdEmployee);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") long employeeId){
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees(){
        List<EmployeeDTO> allEmployees = employeeService.findAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PatchMapping("{id}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable("id") Long emploeeId,
                                                     @RequestBody Map<String, Object> fields){
        EmployeeDTO patchedEmployee = employeeService.patchEmployee(emploeeId, fields);
        return ResponseEntity.ok(patchedEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        String responseStr = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(responseStr);
    }
}
