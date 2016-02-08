package devnoh.demoapp.dao;

import devnoh.demoapp.model.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
    public void testGet() {
        logger.debug("testGet...");
        Employee emp = employeeDao.get(7369);
        Assert.assertNotNull(emp);
        Assert.assertEquals(7369, emp.getEmpNo());
        Assert.assertEquals("SMITH", emp.getName());
        Assert.assertEquals("CLERK", emp.getJob());
        Assert.assertEquals(7902, (int) emp.getMgrNo());
        Assert.assertEquals(new DateTime(1980, 12, 17, 0, 0, 0).toDate().getTime(), emp.getHireDate().getTime());
        Assert.assertEquals(800d, emp.getSalary());
        Assert.assertNull(emp.getCommission());
        Assert.assertEquals(20, emp.getDepartment().getDeptNo());
        Assert.assertNull(employeeDao.get(9900));
    }

    @Test
    public void testSave() {
        logger.debug("testSave...");
        Employee emp = new Employee();
        emp.setName("NOH");
        emp.setJob("DEVELOPER");
        emp.setSalary(1000d);
        emp = employeeDao.save(emp);
        logger.debug(emp);
        Assert.assertTrue(emp.getEmpNo() > 9000);
        List<Employee> emps = employeeDao.getAll();
        Assert.assertEquals(15, emps.size());
    }

}
