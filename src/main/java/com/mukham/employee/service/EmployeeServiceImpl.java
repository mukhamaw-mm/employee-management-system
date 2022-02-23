package com.mukham.employee.service;

import com.mukham.employee.model.entity.Employee;
import com.mukham.employee.model.response.Response;
import com.mukham.employee.model.response.Status;
import com.mukham.employee.repository.EmployeeRepository;
import com.mukham.employee.utility.ConstantUtil;
import com.mukham.employee.utility.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity addEmployee(Employee employee) {

        ResponseEntity responseEntity;
        Response response;
        Status status;

        if (!ValidationUtil.isValidEmail(employee.getEmail())) {
            logger.error("email format is wrong: {}", employee.getEmail());

            status = new Status(ConstantUtil.FAIL, "email format is wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);

            return responseEntity;
        }

        Integer lastId = employeeRepository.getLastId();
        // generate emp id and it will be unique because it's increment by total row of table by retrieving count in above.
        if(lastId ==null)
            lastId =0;
        int increment = lastId + 10001;
        String empId = "EMP_" + increment; //assume that it will format like EMP_10002
        employee.setEmpId(empId);
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());
        employee.setDeleted(false);
        /**
         * Later you can encrypt for security purpose but currently I added as plain text.
         */

        logger.info("Before saving into db: {}", employee);

        employeeRepository.save(employee);

        logger.info("Successfully saved into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;

    }

    @Override
    public ResponseEntity login(String empId, String password) {

        ResponseEntity responseEntity;
        Response response;
        Status status;

        List<Employee> employeeList = employeeRepository.findByEmpIdAndPasswordAndDeleted(empId, password, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "user name or password is wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList.get(0));
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;

    }

    @Override
    public ResponseEntity changePassword(String empId, String currentPwd, String newPwd) {

        ResponseEntity responseEntity;
        Response response;
        Status status;

        List<Employee> employeeList = employeeRepository.findByEmpIdAndPasswordAndDeleted(empId, currentPwd, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "user name or password is wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        Employee employee = employeeList.get(0);
        employee.setPassword(newPwd);
        employee.setUpdatedDate(new Date());

        logger.info("Before saving into db: {}", employee);

        employeeRepository.save(employee);

        logger.info("Successfully saved into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity updateEmployee(Employee employee) {

        ResponseEntity responseEntity;
        Response response;
        Status status;

        if (!ValidationUtil.isValidString(employee.getName()) && !ValidationUtil.isValidString(employee.getAddress()) &&
                !ValidationUtil.isValidString(employee.getPhoneNo()) && !ValidationUtil.isValidString(employee.getEmail())
                && employee.getDeptId() == 0 && employee.getPositionId() == 0 && employee.getRoleId() == 0) {
            status = new Status(ConstantUtil.FAIL, "All fields are null or empty");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employeeList = employeeRepository.findByEmpIdAndDeleted(employee.getEmpId(), false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "input employee id is wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        Employee dbEmp = employeeList.get(0);
        if (ValidationUtil.isValidString(employee.getName()))
            dbEmp.setName(employee.getName());

        if (ValidationUtil.isValidString(employee.getAddress()))
            dbEmp.setAddress(employee.getAddress());

        if (ValidationUtil.isValidString(employee.getPhoneNo()))
            dbEmp.setPhoneNo(employee.getPhoneNo());

        if (ValidationUtil.isValidString(employee.getEmail())) {
            if (ValidationUtil.isValidEmail(employee.getEmail())) {
                dbEmp.setEmail(employee.getEmail());
            } else {
                logger.error("email format is wrong: {}", employee.getEmail());
                status = new Status(ConstantUtil.FAIL, "email format is worng");
                response = new Response(status, null);
                responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
        }

        if (employee.getDeptId() != 0)
            dbEmp.setDeptId(employee.getDeptId());
        if (employee.getRoleId() != 0)
            dbEmp.setRoleId(employee.getRoleId());

        if (employee.getPositionId() != 0)
            dbEmp.setPositionId(employee.getPositionId());

        logger.info("Before saving into db: {}", dbEmp);
        employeeRepository.save(dbEmp);
        logger.info("Successfully updated into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public List<Employee> searchAll() {
        List<Employee> employeeList = employeeRepository.findByDeleted(false);
        logger.info("db response size:{}", employeeList.size());
        return employeeList;
    }

    @Override
    public ResponseEntity searchByName(String name) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(name)){
            status = new Status(ConstantUtil.FAIL, "name is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        name = name+"%"; //change into like query
        List<Employee> employeeList = employeeRepository.findByNameAndDeleted(name, false);

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity searchByEmpId(String empId) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(empId)){
            status = new Status(ConstantUtil.FAIL, "employee id is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employeeList = employeeRepository.findByEmpIdAndDeleted(empId, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "employee id is wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity searchByEmail(String email) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(email)){
            status = new Status(ConstantUtil.FAIL, "email is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        email = email+"%";
        List<Employee> employeeList = employeeRepository.findByEmailAndDeleted(email, false);

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity searchByDepartment(int deptId) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(deptId == 0){
            status = new Status(ConstantUtil.FAIL, "department id  is null.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employee = employeeRepository.findByDeptIdAndDeleted(deptId, false);
        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employee);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity addLeaveBalance(String empId, int leaveCount) {

        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(empId)){
            status = new Status(ConstantUtil.FAIL, "employee id is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employeeList = employeeRepository.findByEmpIdAndDeleted(empId, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "Could not find by Employee Id.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        Employee employee = employeeList.get(0);
        int count = employee.getTotalLeaveBalance() + leaveCount;
        employee.setTotalLeaveBalance(count);
        employee.setUpdatedDate(new Date());

        logger.info("Before saving into db: {}", employee);

        employeeRepository.save(employee);

        logger.info("Successfully saved into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;

    }

    @Override
    public ResponseEntity deductLeaveBalance(String empId, int leaveCount) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(empId)){
            status = new Status(ConstantUtil.FAIL, "employee id is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employeeList = employeeRepository.findByEmpIdAndDeleted(empId, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "Could not find by Employee Id.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

        Employee employee = employeeList.get(0);
        int count = employee.getTotalLeaveBalance() - leaveCount;
        employee.setTotalLeaveBalance(count);
        employee.setUpdatedDate(new Date());

        logger.info("Before saving into db: {}", employee);

        employeeRepository.save(employee);

        logger.info("Successfully saved into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, null);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity deleteByEmpId(String empId) {
        ResponseEntity responseEntity;
        Response response;
        Status status;

        if(!ValidationUtil.isValidString(empId)){
            status = new Status(ConstantUtil.FAIL, "employee id is null or empty.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Employee> employeeList = employeeRepository.findByEmpIdAndDeleted(empId, false);

        if (employeeList.size() < 1) {
            status = new Status(ConstantUtil.FAIL, "Could not find by Employee Id.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (employeeList.size() > 1) {
            status = new Status(ConstantUtil.FAIL, "Something went wrong.");
            response = new Response(status, null);
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        Employee employee = employeeList.get(0);
        employee.setDeleted(true);
        employee.setUpdatedDate(new Date());
        logger.info("Before saving into db: {}", employee);

        employeeRepository.save(employee);

        logger.info("Successfully saved into db.");

        status = new Status(ConstantUtil.SUCCESS, "");
        response = new Response(status, employeeList);
        responseEntity = new ResponseEntity(response, HttpStatus.OK);

        return responseEntity;
    }


}



