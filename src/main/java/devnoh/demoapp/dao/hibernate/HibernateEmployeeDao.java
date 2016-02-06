package devnoh.demoapp.dao.hibernate;

import devnoh.demoapp.dao.*;
import devnoh.demoapp.model.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository("employeeDao")
public class HibernateEmployeeDao extends HibernateGenericDao<Employee, Integer> implements EmployeeDao {

    public HibernateEmployeeDao() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        return (List<Employee>) criteria.list();
    }

    public List<Employee> findEmployeesByDeptNo(int deptNo) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a from Employee a ");
        sql.append("where a.department.deptNo=:deptNo ");
        sql.append("order by a.name asc ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("deptNo", deptNo);
        return query.list();
    }

    /*
    public List<Employee> findEmployeesByDeptNo(int deptNo) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("department.deptNo", deptNo));
        return (List<Employee>) criteria.list();
    }
    */
}
