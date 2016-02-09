package devnoh.demoapp.service;

import devnoh.demoapp.config.*;
import devnoh.demoapp.dao.*;
import devnoh.demoapp.model.*;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import java.util.*;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {HibernateTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class) //@RunWith(MockitoJUnitRunner.class)
public class DemoServiceImplTest {

    protected Logger logger = LogManager.getLogger(getClass());

    @Mock
    DepartmentDao departmentDao;

    @Mock
    EmployeeDao employeeDao;

    @Mock
    Department department;

    @Mock
    Employee employee;

    @InjectMocks
    DemoServiceImpl demoService;

    @Spy
    List<Employee> employees  = new ArrayList<Employee>();

    @Spy
    List<Department> departments = new ArrayList<Department>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // employees
        Employee emp = new Employee();
        emp.setEmpNo(9001);
        emp.setName("EMP1");
        employees.add(emp);
        emp = new Employee();
        emp.setEmpNo(9002);
        emp.setName("EMP2");
        employees.add(emp);

        // departments
        Department dept = new Department();
        dept.setDeptNo(10);
        dept.setName("DEPT1");
        departments.add(dept);
        dept = new Department();
        dept.setDeptNo(20);
        dept.setName("DEPT2");
        departments.add(dept);
    }

    @Test
    public void testSaveDepartment() {
        logger.debug("testSaveDepartment...");
        Department dept = new Department();
        dept.setDeptNo(10);
        when(departmentDao.save(dept)).thenReturn(dept);
        Assert.assertEquals(demoService.saveDepartment(dept), dept);
    }

    @Test
    public void testDeleteDepartment() {
        logger.debug("testDeleteDepartment...");
        doNothing().when(departmentDao).delete(department);
        demoService.deleteDepartment(department);
        verify(departmentDao, atLeastOnce()).delete(department);
    }

    @Test
    public void testGetDepartment() {
        logger.debug("testGetDepartment...");
        Department dept = new Department();
        dept.setDeptNo(10);
        when(departmentDao.get(anyInt())).thenReturn(dept);
        Assert.assertEquals(demoService.getDepartment(dept.getDeptNo()), dept);
    }

    @Test
    public void testGetAllDepartments() {
        logger.debug("testGetAllDepartments...");
        when(departmentDao.getAll()).thenReturn(departments);
        Assert.assertEquals(departments, demoService.getAllDepartments());
        verify(departmentDao, times(1)).getAll();
    }

    @Test
    public void testSaveEmployee() {
        logger.debug("testSaveEmployee...");
        Employee emp = new Employee();
        emp.setEmpNo(9000);
        when(employeeDao.save(emp)).thenReturn(emp);
        Assert.assertEquals(emp, demoService.saveEmployee(emp));
    }

    @Test
    public void testDeleteEmployee() {
        logger.debug("testDeleteEmployee...");
        doNothing().when(employeeDao).delete(employee);
        demoService.deleteEmployee(employee);
        verify(employeeDao, atLeastOnce()).delete(employee);
    }

    @Test
    public void testGetEmployee() {
        logger.debug("testGetEmployee...");
        Employee emp = new Employee();
        emp.setEmpNo(9000);
        when(employeeDao.get(anyInt())).thenReturn(emp);
        Assert.assertEquals(emp, demoService.getEmployee(emp.getEmpNo()));
    }

    @Test
    public void testGetAllEmployees() {
        logger.debug("testGetAllEmployees...");
        when(employeeDao.getAll()).thenReturn(employees);
        Assert.assertEquals(employees, demoService.getAllEmployees());
        verify(employeeDao, times(1)).getAll();
    }

    @Test
    public void testFindEmployeesByDeptNo() {
        logger.debug("testFindEmployeesByDeptNo...");
        when(employeeDao.findEmployeesByDeptNo(10)).thenReturn(employees);
        Assert.assertEquals(employees, demoService.findEmployeesByDeptNo(10));
        verify(employeeDao, times(1)).findEmployeesByDeptNo(10);
    }
}