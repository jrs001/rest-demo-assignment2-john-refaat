package com.itworx.restdemo.service;

import com.itworx.restdemo.exception.ResourceNotFoundException;
import com.itworx.restdemo.model.Employee;
import com.itworx.restdemo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Captor
  private ArgumentCaptor<Employee> employeeCaptor;

  Employee employee;

  @BeforeEach
  void setUp() {
    employee = new Employee("John", "Doe", "j.d@eample.com");
    employee.setId(1L);
  }

  @Test
  void findAll() {
    //given
    List<Employee> employees = new ArrayList<>();
    employees.add(employee);
    given(employeeRepository.findAll()).willReturn(employees);

    //when
    List<Employee> returnedEmployees = employeeService.findAll();

    //then
    then(employeeRepository).should().findAll();
    then(employeeRepository).shouldHaveNoMoreInteractions();

    assertThat(returnedEmployees).hasSize(1);
    assertThat(returnedEmployees.get(0)).isEqualTo(employee);

  }

  @Test
  void findById() throws ResourceNotFoundException {
    //given
      given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));

      //when
      Employee returnedEmployee = employeeService.findById(1L);

      //then
    then(employeeRepository).should().findById(1L);
    then(employeeRepository).shouldHaveNoMoreInteractions();
    assertThat(returnedEmployee).isEqualTo(employee);
  }

  @Test
  void findByIdNotFound() {
    //given
    given(employeeRepository.findById(anyLong())).willReturn(Optional.empty());
    //when

    //then
    assertThrows(ResourceNotFoundException.class, () -> {
       employeeService.findById(1L);
    });
    then(employeeRepository).should().findById(anyLong());
    then(employeeRepository).shouldHaveNoMoreInteractions();
  }
  
  @Test
  void save() {

    Employee NotSavedemployee = new Employee("John", "Doe", "j.d@eample.com");
    //given
    given(employeeRepository.save(any())).willReturn(employee);

    //when
    Employee savedEmployee = employeeService.save(NotSavedemployee);

    //then
    verify(employeeRepository).save(employeeCaptor.capture());
    then(employeeRepository).should().save(NotSavedemployee);
    then(employeeRepository).shouldHaveNoMoreInteractions();

    assertThat(employeeCaptor.getValue()).isEqualTo(NotSavedemployee);
    assertThat(savedEmployee).isEqualTo(employee);

  }

  @Test
  void delete() {
    //given

    //when
    employeeService.delete(employee);

    //then
    verify(employeeRepository).delete(employeeCaptor.capture());
    then(employeeRepository).should().delete(any(Employee.class));
    then(employeeRepository).shouldHaveNoMoreInteractions();

    assertThat(employeeCaptor.getValue()).isEqualTo(employee);
  }
}