package com.mukham.employee.service;

import com.mukham.employee.model.entity.Department;
import com.mukham.employee.model.entity.Position;
import com.mukham.employee.model.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConfigService {
    ResponseEntity addDepartment(String name);
    List<Department> getAllDepartment();
    ResponseEntity addPosition(String  name);
    List<Position> getAllPosition();
    ResponseEntity addRole(String  name);
    List<Role> getAllRole();
}
