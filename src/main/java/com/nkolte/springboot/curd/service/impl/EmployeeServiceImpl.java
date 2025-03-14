package com.nkolte.springboot.curd.service.impl;

import com.nkolte.springboot.curd.dto.EmployeeDTO;
import com.nkolte.springboot.curd.entity.Employee;
import com.nkolte.springboot.curd.exception.EmailAlreadyExistsException;
import com.nkolte.springboot.curd.exception.EmployeeNotFoundException;
import com.nkolte.springboot.curd.repository.EmployeeRepository;
import com.nkolte.springboot.curd.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        boolean isEmailpresent = employeeRepository.findByEmailId(employeeDTO.getEmailId())
                .isPresent();

        if (isEmailpresent){
            throw new EmailAlreadyExistsException(employeeDTO.getEmailId());
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmpDTO = modelMapper.map(savedEmployee, EmployeeDTO.class);

        return savedEmpDTO;
    }

    @Override
    public EmployeeDTO findEmployeeById(long employeeId) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee foundEmployee = optionalEmployee.orElseThrow(()-> new EmployeeNotFoundException(employeeId));
        EmployeeDTO foundEmployeeDTO = modelMapper.map(foundEmployee, EmployeeDTO.class);

        return foundEmployeeDTO;
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeByEmailId(String emailId) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId(emailId);
        return null;
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        return employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {

        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException(employeeId));

        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setLastName(existingEmployee.getLastName());
        existingEmployee.setEmailId(existingEmployee.getEmailId());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        EmployeeDTO savedEmployeeDTO = modelMapper.map(updatedEmployee, EmployeeDTO.class);

        return savedEmployeeDTO;
    }

    @Override
    public EmployeeDTO patchEmployee(Long employeeId, Map<String, Object>fields) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new EmployeeNotFoundException(employeeId));

        fields.forEach((key, value)->{
            Field field = ReflectionUtils.findField(Employee.class,key);

            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingEmployee, value);
            }
        });

        Employee patchedEmployee = employeeRepository.save(existingEmployee);
        EmployeeDTO patchedEmployeeDTO = modelMapper.map(patchedEmployee,EmployeeDTO.class);

        return patchedEmployeeDTO;

    }

    @Override
    public String deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return String.format("Employee with id: %s is deleted successfully.",employeeId);
    }
}
