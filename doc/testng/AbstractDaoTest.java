package devnoh.demoapp.dao.testng;

import devnoh.demoapp.config.*;
import org.apache.logging.log4j.*;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.operation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.testng.*;
import org.springframework.test.context.web.*;
import org.testng.annotations.*;

import javax.sql.*;

@WebAppConfiguration
@ContextConfiguration(classes = {HibernateTestConfig.class})
public abstract class AbstractDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected DataSource dataSource;

    @BeforeMethod
    public void setUp() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
    }

    protected abstract IDataSet getDataSet() throws Exception;
}
