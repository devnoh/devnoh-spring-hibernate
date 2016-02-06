package devnoh.demoapp.dao;

import java.io.*;
import java.util.*;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for your domain objects.
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type.
     *
     * @return List of populated objects
     */
    public List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier.
     *
     * @param id the id of the object to get
     * @return a populated object
     */
    public T get(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     *
     * @param entity the object to save
     * @return the persisted object
     */
    public T save(T entity);

    /**
     * Generic method to delete an object
     *
     * @param entity the object to remove
     */
    public void delete(T entity);

//    /**
//     * Generic method to delete an object
//     *
//     * @param id the identifier (primary key) of the object to remove
//     */
//    public void delete(PK id);

    /**
     * Checks for existence of an object
     *
     * @param id the id of the object
     * @return - true if it exists, false if it doesn't
     */
    public boolean exists(PK id);
}
