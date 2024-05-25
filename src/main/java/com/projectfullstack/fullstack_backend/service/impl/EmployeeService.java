package com.projectfullstack.fullstack_backend.service.impl;

import com.projectfullstack.fullstack_backend.dto.EmployeeDto;
import com.projectfullstack.fullstack_backend.exception.ResourceNotFoundException;
import com.projectfullstack.fullstack_backend.mapper.EmployeeMapper;
import com.projectfullstack.fullstack_backend.model.Employee;
import com.projectfullstack.fullstack_backend.repository.IEmployeeRepository;
import com.projectfullstack.fullstack_backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employeeSave = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employeeSave);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exists with given id " + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee id not found, can't update!!!"));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee id not found, can't delete!!!"));
        employeeRepository.deleteById(id);
    }
}
