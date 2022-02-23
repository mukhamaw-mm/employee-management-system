package com.mukham.employee.repository;

import com.mukham.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    @Query(nativeQuery = true, value = "select id from employees order by id desc limit 1")
    Integer getLastId();

    List<Employee> findByEmpIdAndPasswordAndDeleted(String empId, String pwd, boolean isDeleted);

    List<Employee> findByEmpIdAndDeleted(String empId, boolean deleted);

    @Query(nativeQuery = true, value = "select * from employees where name like :name and deleted=:del")
    List<Employee> findByNameAndDeleted(@Param ("name")String name, @Param("del")boolean deleted);

    List<Employee> findByDeleted( boolean deleted);

    @Query(nativeQuery = true, value = "select * from employees where email like :email and deleted=:del")
    List<Employee> findByEmailAndDeleted(@Param("email")String email, @Param("del")boolean deleted);

    List<Employee> findByDeptIdAndDeleted(int deptId, boolean deleted);


}
