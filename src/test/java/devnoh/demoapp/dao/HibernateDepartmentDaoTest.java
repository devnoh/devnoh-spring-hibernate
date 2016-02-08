package devnoh.demoapp.dao;

import devnoh.demoapp.model.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.springframework.beans.factory.annotation.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class HibernateDepartmentDaoTest extends AbstractDaoTest {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        logger.debug("getDataSet... dept.xml");
        IDataSet dataSet = new XmlDataSet(this.getClass().getClassLoader().getResourceAsStream("dept.xml"));
        return dataSet;
    }

    @Test
    public void testGet() {
        logger.debug("testGet...");
        Department dept = departmentDao.get(10);
        Assert.assertNotNull(dept);
        Assert.assertEquals(10, dept.getDeptNo());
        Assert.assertEquals("ACCOUNTING", dept.getName());
        Assert.assertEquals("NEW YORK", dept.getLocation());
        Assert.assertNull(departmentDao.get(50));
    }
}
