package devnoh.demoapp.dao;

import devnoh.demoapp.config.*;
import org.apache.logging.log4j.*;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.operation.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import javax.sql.*;

@ContextConfiguration(classes = {HibernateTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
    }

    protected abstract IDataSet getDataSet() throws Exception;
}
