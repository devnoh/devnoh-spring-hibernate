package devnoh.demoapp.service;

import devnoh.demoapp.dao.*;
import devnoh.demoapp.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("demoService")
@Transactional
public class DemoServiceImpl implements DemoService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //
    // Department
    //

    public Department saveDepartment(Department deptartment) {
        return departmentDao.save(deptartment);
    }

    public void deleteDepartment(Department deptartment) {
        departmentDao.delete(deptartment);
    }

    public Department getDepartment(int deptNo) {
        return departmentDao.get(deptNo);
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAll();
    }

    //
    // Employee
    //

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    public Employee getEmployee(int empNo) {
        return employeeDao.get(empNo);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    public List<Employee> findEmployeesByDeptNo(int deptNo) {
        return employeeDao.findEmployeesByDeptNo(deptNo);
    }
}
