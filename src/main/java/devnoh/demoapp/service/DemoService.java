package devnoh.demoapp.service;

import devnoh.demoapp.model.*;

import java.util.*;

public interface DemoService {

    //
    // Department
    //

    Department saveDepartment(Department deptartment);

    void deleteDepartment(Department deptartment);

    Department getDepartment(int deptNo);

    List<Department> getAllDepartments();

    //
    // Employee
    //

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployee(int empNo);

    List<Employee> getAllEmployees();

    List<Employee> findEmployeesByDeptNo(int deptNo);

}
