package com.mukham.employee.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empId;
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private String password;
    private int totalLeaveBalance;
    private boolean deleted;
    private int positionId;
    private int deptId;
    private int roleId;
    private Date createdDate;
    private Date updatedDate;
}
