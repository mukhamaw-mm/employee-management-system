package com.mukham.employee.controller;

import com.mukham.employee.model.entity.Employee;
import com.mukham.employee.model.response.Response;
import com.mukham.employee.model.response.Status;
import com.mukham.employee.service.EmployeeService;
import com.mukham.employee.utility.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        logger.info("!!! Start add employee method !!!");
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.addEmployee(employee);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End add employee method !!!");
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String empId, @RequestParam String password) {
        logger.info("!!! Start login method !!!");
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.login(empId, password);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End login method !!!");
        return responseEntity;
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestParam String empId, @RequestParam String currentPwd, @RequestParam String newPwd) {
        logger.info("!!! Start changePassword method !!!");
        logger.info("Request - empId: {}, currentPwd: {}, newPwd: {}", empId, currentPwd, newPwd);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.changePassword(empId, currentPwd, newPwd);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End changePassword method !!!");
        return responseEntity;
    }


    @PostMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        logger.info("!!! Start update method !!!");
        logger.info("Request - employee: {}", employee);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.updateEmployee(employee);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End update method !!!");
        return responseEntity;

    }


    @GetMapping("/searchAll")
    public ResponseEntity searchAll() {
        logger.info("!!! Start searchALl method !!!");

        ResponseEntity responseEntity;
        try {

            List<Employee> employeeList = employeeService.searchAll();
            responseEntity = new ResponseEntity(employeeList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End searchALL method !!!");
        return responseEntity;
    }

    @GetMapping("/searchByName")
    public ResponseEntity searchByName(@RequestParam String name) {
        logger.info("!!! Start searchByName method !!!");
        logger.info("Request - name: {}", name);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.searchByName(name);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End searchByName method !!!");
        return responseEntity;
    }

    @GetMapping("/searchByEmpId")
    public ResponseEntity searchByEmpId(@RequestParam String empId) {
        logger.info("!!! Start searchByEmpId method !!!");
        logger.info("Request - name: {}", empId);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.searchByEmpId(empId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End searchByEmpId method !!!");
        return responseEntity;
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity searchByEmail(@RequestParam String email) {
        logger.info("!!! Start searchByEmail method !!!");
        logger.info("Request - name: {}", email);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.searchByEmail(email);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End searchByEmail method !!!");
        return responseEntity;

    }

    @GetMapping("/searchByDepartment")
    public ResponseEntity searchByDepartment(@RequestParam int deptId) {
        logger.info("!!! Start searchByDepartmentId method !!!");
        logger.info("Request - name: {}", deptId);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.searchByDepartment(deptId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End searchByDepartmentId method !!!");
        return responseEntity;
    }

    @PostMapping("/addLeaveBalance")
    public ResponseEntity addEmployee(@RequestParam String empId, int leaveCount) {
        logger.info("!!! Start addLeaveBalance method !!!");
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.addLeaveBalance(empId, leaveCount);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End addLeaveBalance method !!!");
        return responseEntity;
    }

    @PostMapping("/deductLeaveBalance")
    public ResponseEntity deductLeaveBalance(String empId, int leaveCount) {
        logger.info("!!! Start deductLeaveBalance method !!!");
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.deductLeaveBalance(empId, leaveCount);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End deductLeaveBalance method !!!");
        return responseEntity;
    }


    @PostMapping("/deleteByEmpId")
    public ResponseEntity deleteByEmpId(String empId){
        logger.info("!!! Start deleteByEmpId method !!!");
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.deleteByEmpId(empId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End deleteByEmpId method !!!");
        return responseEntity;
    }


}