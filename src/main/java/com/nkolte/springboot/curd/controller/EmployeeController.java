package com.nkolte.springboot.curd.controller;

import com.nkolte.springboot.curd.dto.EmployeeDTO;
import com.nkolte.springboot.curd.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Tag(
        name = "CURD operations for employee resource.",
        description = "create/save, update, get, get all, delete operations on employee resource "
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(
            summary = "Create user REST API",
            description = "Saves new user to DB."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Resource created."
    )
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(createdEmployee);
    }

    @Operation(
            summary = "Find Employee by id REST API.",
            description = "Returns the employee with matching id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") long employeeId){
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    @Operation(
            summary = "get all employees.",
            description = "Returns the all the employees in DB."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @GetMapping(value = {"","/"})
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees(){
        List<EmployeeDTO> allEmployees = employeeService.findAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @Operation(
            summary = "Update Employee by id REST API.",
            description = "Updates the information of employee with matching id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }
    @Operation(
            summary = "Patch Employee by id REST API.",
            description = "Just Updates the specific given information of employee with matching id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @PatchMapping("{id}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable("id") Long emploeeId,
                                                     @RequestBody Map<String, Object> fields){
        EmployeeDTO patchedEmployee = employeeService.patchEmployee(emploeeId, fields);
        return ResponseEntity.ok(patchedEmployee);
    }

    @Operation(
            summary = "Deletes Employee by id REST API.",
            description = "Deletes the information of employee with matching id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        String responseStr = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(responseStr);
    }
}
