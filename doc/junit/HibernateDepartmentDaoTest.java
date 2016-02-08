package devnoh.demoapp.dao;

import devnoh.demoapp.config.*;
import org.apache.logging.log4j.*;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.dbunit.operation.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.transaction.*;
import org.springframework.test.context.web.*;

import javax.sql.*;
import javax.transaction.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringRootConfig.class, HibernateTestConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@Transactional
public class HibernateDepartmentDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static Logger logger = LogManager.getLogger(HibernateDepartmentDaoTest.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    DepartmentDao departmentDao;

    @BeforeTransaction
    public void beforeTransaction() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
    }

    protected IDataSet getDataSet() throws Exception {
        logger.debug("getDataSet... dept.xml");
        IDataSet dataSet = new XmlDataSet(this.getClass().getClassLoader().getResourceAsStream("dept.xml"));
        return dataSet;
    }

    @AfterTransaction
    public void afterTransaction() {

    }

    @Test
    public void testGet() {
        logger.debug("testGet...");
        Assert.assertNotNull(departmentDao.get(10));
        Assert.assertNull(departmentDao.get(50));
    }

}
