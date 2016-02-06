package devnoh.demoapp.dao.hibernate;

import devnoh.demoapp.dao.*;
import devnoh.demoapp.model.*;
import org.springframework.stereotype.*;

@Repository("departmentDao")
public class HibernateDepartmentDao extends HibernateGenericDao<Department, Integer> implements DepartmentDao {

    public HibernateDepartmentDao() {
        super(Department.class);
    }
}
