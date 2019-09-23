package com.itworx.restdemo.service;

import com.itworx.restdemo.exception.ResourceNotFoundException;
import com.itworx.restdemo.model.Employee;
import com.itworx.restdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author john.refaat
 * @date 7/22/2019
 **/

@Service
public class EmployeeServiceImpl implements EmployeeService{

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(Long employeeId) throws ResourceNotFoundException {
   return employeeRepository.findById(employeeId)
           .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
  }

  @Override
  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void delete(Employee employee) {
    employeeRepository.delete(employee);
  }
}
