package com.projectfullstack.fullstack_backend.mapper;

import com.projectfullstack.fullstack_backend.dto.EmployeeDto;
import com.projectfullstack.fullstack_backend.model.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new  Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
