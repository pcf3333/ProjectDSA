package edu.upc.dsa.util;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.List;

public class QueryHelper {

    public static String createQueryINSERT(Object entity,String tableName){
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(tableName).append(" (");

        String [] fields = ObjectHelper.getFields(entity);
        Field[] real_fields=entity.getClass().getDeclaredFields();

        for (String field: fields) {
            sb.append(field);
            if (field!=fields[fields.length-1]) {
                sb.append(", ");
            }
        }

        sb.append(") VALUES (");

        for (Field field: real_fields) {
            field.setAccessible(true);
            try {
                if (field.get(entity) instanceof List) {
                    sb.append("'");
                    sb.append(object2Json(field.get(entity)));
                    sb.append("'");
                }
                else if (field.get(entity) instanceof String){
                    sb.append('"');
                    sb.append(field.get(entity));
                    sb.append('"');
                }
                else{
                    sb.append(field.get(entity));
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            if (field!=real_fields[real_fields.length-1]) {
                sb.append(", ");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    public static String createSELECTALL(String table){
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM ");
        sb.append(table);

        return sb.toString();
    }

    public static String createQuerySELECT(String table, String comparator, String value, String element) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ").append(element);
        sb.append(" FROM ").append(table);
        sb.append(" WHERE ").append(comparator);
        sb.append(" = '").append(value).append("'");

        return sb.toString();
    }

    public static String createQueryDELETE(String table, String comparator, String value){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(table);
        sb.append(" WHERE ").append(comparator);
        sb.append(" = '").append(value).append("'");
        return sb.toString();
    }

    public static String createQueryUPDATE(String table, String comparator, Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(table);
        sb.append(" SET ");

        String [] fields = ObjectHelper.getFields(entity);
        Field[] real_fields=entity.getClass().getDeclaredFields();

        int index=0;
        String value="";

        for (String field: fields) {
            sb.append(field).append(" = ");
            Field valor = real_fields[index];
            valor.setAccessible(true);
            try {
                if (valor.get(entity) instanceof List) {
                    sb.append("'");
                    sb.append(object2Json(valor.get(entity)));
                    sb.append("'");
                }
                else if (valor.get(entity) instanceof String){
                    sb.append('"');
                    sb.append(valor.get(entity));
                    sb.append('"');
                }
                else{
                    sb.append(valor.get(entity));
                }

                if (field.equals(comparator)){
                    value=valor.get(entity).toString();
                }
            }

            catch (Exception e){
                System.out.println(e);
            }

            if (field!=fields[fields.length-1]) {
                sb.append(", ");
            }

            index++;
        }

        sb.append(" WHERE ").append(comparator);
        sb.append(" = '").append(value).append("'");

        return sb.toString();
    }

    public static String object2Json(Object o){
        Gson g = new Gson();
        return g.toJson(o);
    }

}
