package com.mukham.employee.service;

import com.mukham.employee.model.entity.Department;
import com.mukham.employee.model.entity.Position;
import com.mukham.employee.model.entity.Role;
import com.mukham.employee.model.response.Response;
import com.mukham.employee.model.response.Status;
import com.mukham.employee.repository.DepartmentRepository;
import com.mukham.employee.repository.PositionRepository;
import com.mukham.employee.repository.RoleRepository;
import com.mukham.employee.utility.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity addDepartment(String name) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        Department department = new Department();
        department.setDepartmentName(name);

        logger.info("Before saving into db: {}", department);

        departmentRepository.save(department);

        logger.info("Successfully saved into db.");
        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;


    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> departmentList =departmentRepository.findAll();
        logger.info("db response size:{}", departmentList.size());
        return departmentList;
    }


    @Override
    public ResponseEntity addPosition(String name) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        Position position = new Position();
        position.setPositionName(name);
        logger.info("Before saving into db: {}", position);

        positionRepository.save(position);

        logger.info("Successfully saved into db.");
        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public List<Position> getAllPosition() {
        List<Position> positionList =positionRepository.findAll();
        logger.info("db response size:{}", positionList.size());
        return positionList;
    }


    @Override
    public ResponseEntity addRole(String name) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        Role role = new Role();
        role.setRoleName(name);
        logger.info("Before saving into db: {}", role);

        roleRepository.save(role);
        logger.info("Successfully saved into db.");
        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roleList =roleRepository.findAll();
        logger.info("db response size:{}", roleList.size());
        return roleList;
    }
}
