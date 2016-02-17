package devnoh.demoapp.config;

import com.mchange.v2.c3p0.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.*;
import org.springframework.orm.hibernate4.*;
import org.springframework.transaction.annotation.*;

import javax.sql.*;
import java.beans.*;
import java.io.*;
import java.util.*;

@Configuration
@EnableTransactionManagement
@ComponentScan({"devnoh.demoapp.dao"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {

    @Autowired
    private Environment env;

    /*
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    */

    /**
     * https://commons.apache.org/proper/commons-dbcp/
     * @return a pooled data source
     */
    /*
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(20);
        dataSource.setMaxWaitMillis(30000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setValidationQuery(env.getProperty("jdbc.testQuery"));
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        return dataSource;
    }
    */

    /**
     * http://www.mchange.com/projects/c3p0/
     *
     * @return a pooled data source
     */
    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
            dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            dataSource.setUser(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
            dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("jdbc.initialPoolSize")));
            dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("jdbc.acquireIncrement")));
            dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("jdbc.minPoolSize")));
            dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("jdbc.maxPoolSize")));
            dataSource.setMaxIdleTime(Integer.valueOf(env.getProperty("jdbc.maxIdleTime")));
            dataSource.setMaxStatements(Integer.valueOf(env.getProperty("jdbc.maxStatements")));
            dataSource.setPreferredTestQuery(env.getProperty("jdbc.testQuery"));
            dataSource.setIdleConnectionTestPeriod(Integer.valueOf(env.getProperty("jdbc.testPeriod")));
            dataSource.setTestConnectionOnCheckin(Boolean.valueOf(env.getProperty("jdbc.testOnCheckin")));
            dataSource.setTestConnectionOnCheckout(Boolean.valueOf(env.getProperty("jdbc.testOnCheckout")));
            return dataSource;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.enable_lazy_load_no_trans", env.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        properties.put("hibernate.id.new_generator_mappings", env.getRequiredProperty("hibernate.id.new_generator_mappings"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"devnoh.demoapp.model"});
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
