package devnoh.demoapp.dao;

import devnoh.demoapp.model.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.joda.time.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class HibernateEmployeeDaoTest extends AbstractDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[]{
                new XmlDataSet(this.getClass().getClassLoader().getResourceAsStream("dept.xml")),
                new XmlDataSet(this.getClass().getClassLoader().getResourceAsStream("emp.xml"))
        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void testGetAll() {
        logger.debug("testGetAll...");
        List<Employee> emps = employeeDao.getAll();
        Assert.assertEquals(emps.size(), 14);
    }

    @Test
    public void testGet() {
        logger.debug("testGet...");
        Employee emp = employeeDao.get(7369);
        Assert.assertNotNull(emp);
        Assert.assertEquals(7369, (int) emp.getEmpNo());
        Assert.assertEquals("SMITH", emp.getName());
        Assert.assertEquals("CLERK", emp.getJob());
        Assert.assertEquals(7902, (int) emp.getMgrNo());
        Assert.assertEquals(new DateTime(1980, 12, 17, 0, 0, 0).toDate().getTime(), emp.getHireDate().getTime());
        Assert.assertEquals(800d, emp.getSalary(), 0);
        Assert.assertNull(emp.getCommission());
        Assert.assertEquals(20, (int) emp.getDepartment().getDeptNo());
        Assert.assertNull(employeeDao.get(9900));
    }

    @Test
    public void testSave() {
        logger.debug("testSave...");
        Employee emp = new Employee();
        emp.setEmpNo(9001);
        emp.setName("SEHWAN");
        emp.setJob("DEVELOPER");
        emp.setSalary(1000d);
        emp = employeeDao.save(emp);
        logger.debug(emp);
        Assert.assertTrue(emp.getEmpNo() == 9001);
        List<Employee> emps = employeeDao.getAll();
        Assert.assertEquals(15, emps.size());
    }

    @Test
    public void testDelete() {
        logger.debug("testDelete...");
        Employee emp = employeeDao.get(7369);
        employeeDao.delete(emp);
        Assert.assertNull(employeeDao.get(7369));
        List<Employee> emps = employeeDao.getAll();
        Assert.assertEquals(13, emps.size());
    }

    @Test
    public void testExists() {
        logger.debug("testExists...");
        Assert.assertTrue(employeeDao.exists(7369));
        Assert.assertFalse(employeeDao.exists(9000));
    }

    @Test
    public void testFindEmployeesByDeptNo() {
        logger.debug("testFindEmployeesByDeptNo...");
        List<Employee> emps = employeeDao.findEmployeesByDeptNo(10);
        Assert.assertEquals(3, emps.size());
    }
}
