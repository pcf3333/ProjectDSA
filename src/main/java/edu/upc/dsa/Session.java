package edu.upc.dsa;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity, String tableName);
    void close();
    Object get(Class theClass, int ID);
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
    ResultSet simpleQuery(String query);
    int insertQuery(String query);
}
