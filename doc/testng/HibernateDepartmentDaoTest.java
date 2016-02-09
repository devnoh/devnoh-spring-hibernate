package devnoh.demoapp.dao.testng;

import devnoh.demoapp.dao.*;
import devnoh.demoapp.model.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.springframework.beans.factory.annotation.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class HibernateDepartmentDaoTest extends devnoh.demoapp.dao.AbstractDaoTest {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        logger.debug("getDataSet... dept.xml");
        IDataSet dataSet = new XmlDataSet(this.getClass().getClassLoader().getResourceAsStream("dept.xml"));
        return dataSet;
    }

    @Test
    public void testGetAll() {
        logger.debug("testGetAll...");
        List<Department> depts = departmentDao.getAll();
        Assert.assertEquals(depts.size(), 4);
    }

    @Test
    public void testGet() {
        logger.debug("testGet...");
        Department dept = departmentDao.get(10);
        Assert.assertNotNull(dept);
        Assert.assertEquals((int) dept.getDeptNo(), 10);
        Assert.assertEquals("ACCOUNTING", dept.getName());
        Assert.assertEquals("NEW YORK", dept.getLocation());
        Assert.assertNull(departmentDao.get(50));
    }

    @Test
    public void testSave() {
        logger.debug("testSave...");
        Department dept = new Department();
        dept.setDeptNo(50);
        dept.setName("IT");
        dept.setLocation("CERRITOS");
        dept = departmentDao.save(dept);
        logger.debug(dept);
        Assert.assertTrue(dept.getDeptNo() == 50);
        List<Department> depts = departmentDao.getAll();
        Assert.assertEquals(depts.size(), 5);
    }

    @Test
    public void testDelete() {
        logger.debug("testDelete...");
        Department dept = departmentDao.get(10);
        departmentDao.delete(dept);
        Assert.assertNull(departmentDao.get(10));
        List<Department> depts = departmentDao.getAll();
        Assert.assertEquals(depts.size(), 3);
    }

    @Test
    public void testExists() {
        logger.debug("testExists...");
        Assert.assertTrue(departmentDao.exists(10));
        Assert.assertFalse(departmentDao.exists(50));
    }
}
