package devnoh.demoapp.dao.hibernate;

import devnoh.demoapp.dao.*;
import org.apache.logging.log4j.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;

import java.io.*;
import java.util.*;

public class HibernateGenericDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    protected final Logger logger = LogManager.getLogger(getClass());

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(entityClass);
    }

    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param entityClass the class type you'd like to persist
     */
    public HibernateGenericDao(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return getSession().createCriteria(entityClass).list();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T entity) {
        return (T) getSession().merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }

//    /**
//     * {@inheritDoc}
//     */
//    public void delete(PK id) {
//        getSession().delete(this.get(id));
//    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return get(id) != null;
    }
}