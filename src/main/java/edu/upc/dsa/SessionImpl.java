package edu.upc.dsa;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity, String tableName){

        String insertQuery = QueryHelper.createQueryINSERT(entity,tableName);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {

    }

    public Object get(Class theClass, int ID) {
        return null;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

    public ResultSet simpleQuery(String query) {
        try {
            System.out.println(query);
            Statement st = conn.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
               e.printStackTrace();
    		return null;
        }

    }

    public int insertQuery(String query) {
        try {
            System.out.println(query);
            Statement st = conn.createStatement();
            return st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


}
