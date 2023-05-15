package estore.dao;

import java.sql.Connection;
import estore.DatabaseManager;
import estore.models.admain;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class admainDao implements Dao<admain> {

    private static admainDao instance;

    private admainDao() {

    }

    public static admainDao getInstance() {
        if (instance == null) {
            instance = new admainDao();
        }
        return instance;
    }

    private static admain getResults(ResultSet r) {
        admain admain = new admain();
        try {
            admain.setProd_code(Integer.valueOf(r.getString("pCode")));
            admain.setProd_name(r.getString("pName"));
            admain.setTotal(Integer.valueOf(r.getString("total")));
            admain.setCurrency(Integer.valueOf(r.getString("currency")));
            admain.setProd_name(r.getString("personName"));
            return admain;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Optional<admain> get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<admain> getAll() {
        List<admain> admains = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (PreparedStatement getAllStatement = connection.prepareStatement("SELECT *FROM admain")) {
            ResultSet rs = getAllStatement.executeQuery();
            while (rs.next()) {
                admains.add(getResults(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return admains;
    }

    @Override
    public void save(admain t) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (
                 PreparedStatement getAllStatement = connection.prepareStatement("insert into admain \n"
                        + "select prod_code,prod_name,total,currency,pName\n"
                        + "From product\n"
                        + "join person\n"
                        + "where product.pID=person.pID")) {
            getAllStatement.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    @Override
    public admain update(admain t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(admain t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<admain> get(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
