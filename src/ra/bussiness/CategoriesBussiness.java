package ra.bussiness;

import ra.entity.Categories;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CategoriesBussiness {
    public static List<Categories> getAllCategories() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalogid"));
                catalog.setCatalogName(rs.getString("catalogname"));
                catalog.setPriority(rs.getInt("priority"));
                catalog.setCatalogStatus(rs.getBoolean("catalogstatus"));
                listCategories.add(catalog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static List<Categories> getAllCategoriesSort() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_categories_sort()}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalogid"));
                catalog.setCatalogName(rs.getString("catalogname"));
                catalog.setPriority(rs.getInt("priority"));
                catalog.setCatalogStatus(rs.getBoolean("catalogstatus"));
                listCategories.add(catalog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static boolean createCatalog(Categories catalog){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_categories(?,?,?)}");
            callSt.setString(1,catalog.getCatalogName());
            callSt.setInt(2,catalog.getPriority());
            callSt.setBoolean(3,catalog.isCatalogStatus());
            //catalog với trường birthData: java.util.Date --> java.sql.date
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public static boolean updateCategories(Categories catalog){
        Connection conn = null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_categories(?,?,?,?,?)}");
            //set tham số vào
            callSt.setInt(1,catalog.getCatalogId());
            callSt.setString(2,catalog.getCatalogName());
            callSt.setInt(3,catalog.getPriority());
            callSt.setBoolean(4,catalog.isCatalogStatus());
            //Đăng ký tham số ra
            callSt.registerOutParameter(5, Types.INTEGER);
            //Thực hiện gọi procedure
            callSt.execute();
            //Lấy giá trị tham số trả ra
            result = callSt.getInt(5);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        if (result>0){
            return true;
        }
        return false;
    }

    public static List<Categories> searchCategorieByName(String catalogName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_catalog_by_name(?)}");
            callSt.setString(1,catalogName);
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalogid"));
                catalog.setCatalogName(rs.getString("catalogname"));
                catalog.setPriority(rs.getInt("priority"));
                catalog.setCatalogStatus(rs.getBoolean("catalogstatus"));
                listCategories.add(catalog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static boolean updateCategoriesStatus(int catalogId, boolean status){
        Connection conn = null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_catalog_status(?,?,?)}");
            //set tham số vào
            callSt.setInt(1,catalogId);
            callSt.setBoolean(2,status);
            //Đăng ký tham số ra
            callSt.registerOutParameter(3, Types.INTEGER);
            //Thực hiện gọi procedure
            callSt.execute();
            //Lấy giá trị tham số trả ra
            result = callSt.getInt(3);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        if (result>0){
            return true;
        }
        return false;
    }
}
