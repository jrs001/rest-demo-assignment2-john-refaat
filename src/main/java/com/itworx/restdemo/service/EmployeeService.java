package com.itworx.restdemo.service;

import com.itworx.restdemo.exception.ResourceNotFoundException;
import com.itworx.restdemo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  List<Employee> findAll();

  Employee findById(Long employeeId) throws ResourceNotFoundException;

  Employee save(Employee employee);

  void delete(Employee employee);
}
