package com.mukham.employee.service;

import com.mukham.employee.model.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity addEmployee(Employee employee);
    ResponseEntity login(String empId, String password);
    ResponseEntity changePassword(String empId, String currentPwd, String newPwd);
    ResponseEntity updateEmployee(Employee employee);
    List<Employee> searchAll();
    ResponseEntity searchByName(String name);
    ResponseEntity searchByEmpId(String empId);
    ResponseEntity searchByEmail(String email);
    ResponseEntity searchByDepartment(int departmentId);
    ResponseEntity addLeaveBalance(String empId, int leaveCount);
    ResponseEntity deductLeaveBalance(String empId, int leaveCount);
    ResponseEntity deleteByEmpId(String empId);

}
