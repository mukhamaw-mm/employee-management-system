package com.mukham.employee.controller;

import com.mukham.employee.model.entity.Department;
import com.mukham.employee.model.entity.Position;
import com.mukham.employee.model.entity.Role;
import com.mukham.employee.model.response.Response;
import com.mukham.employee.model.response.Status;
import com.mukham.employee.service.ConfigService;
import com.mukham.employee.utility.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

   @Autowired
    ConfigService configService;

    @PostMapping("/addDepartment")
    public ResponseEntity addDepartment(@RequestParam String name) {
        logger.info("!!! Start add department method !!!");
        ResponseEntity responseEntity;

        try {

            // call service to add department
            responseEntity = configService.addDepartment(name);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End add department method !!!");
        return responseEntity;

    }

    @GetMapping("/getAllDepartment")
    public ResponseEntity getAllDepartment() {
        logger.info("!!! Start get aLl department method !!!");
        ResponseEntity responseEntity;

        try {
            List<Department> departmentList = configService.getAllDepartment();
            responseEntity = new ResponseEntity(departmentList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End get all department method !!!");
        return responseEntity;
    }

    @PostMapping("/addPosition")
    public ResponseEntity addPosition(@RequestParam String name) {
        logger.info("!!! Start add position method !!!");
        ResponseEntity responseEntity;
        try {
            responseEntity = configService.addPosition(name);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End add position method !!!");
        return responseEntity;
    }


    @GetMapping("/getAllPosition")
    public ResponseEntity getAllPosition() {
        logger.info("!!! Start get all position method !!!");
        ResponseEntity responseEntity;
        try {
            List<Position> positionList = configService.getAllPosition();
            responseEntity = new ResponseEntity(positionList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End get all position method !!!");
        return responseEntity;

    }

    @PostMapping("/addRole")
    public ResponseEntity addRole(@RequestParam String name) {
        logger.info("!!! Start add role method !!!");
        ResponseEntity responseEntity;
        try {
            responseEntity = configService.addRole(name);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End add role method !!!");
        return responseEntity;
    }

    @GetMapping("/getAllRole")
    public ResponseEntity getAllRole() {
        logger.info("!!! Start get all role method !!!");
        ResponseEntity responseEntity;
        try {
            List<Role> roleList = configService.getAllRole();
            responseEntity = new ResponseEntity(roleList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

            Status status = new Status(ConstantUtil.FAIL, e.getMessage());
            Response response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response: {}", responseEntity.getBody());
        logger.info("!!! End get all role method !!!");
        return responseEntity;
    }
}
