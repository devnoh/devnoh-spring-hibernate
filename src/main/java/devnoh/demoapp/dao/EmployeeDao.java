package devnoh.demoapp.dao;

import devnoh.demoapp.model.*;

import java.util.*;

public interface EmployeeDao extends GenericDao<Employee, Integer> {

    List<Employee> findEmployeesByDeptNo(int deptNo);

}
