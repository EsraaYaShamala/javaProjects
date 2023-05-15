package estore.dao;

import estore.DatabaseManager;
import estore.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDao implements Dao<Product> {
    private static ProductDao instance;

    private ProductDao() {

    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    @Override
    public void save(Product product) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (PreparedStatement saveStatement = connection.prepareStatement(
                        "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            saveStatement.setString(1, product.getProd_code()+"");
            saveStatement.setString(2, product.getProd_name());
            saveStatement.setString(3, product.getProd_url());
            saveStatement.setString(4, product.getProd_picture());
            saveStatement.setString(5, product.getProductionDate());
            saveStatement.setString(6, product.getPrice()+"");
            saveStatement.setString(7, product.getCurrency()+"");
            saveStatement.setString(8, product.getQuantity()+"");
            saveStatement.setString(9, product.getpID()+"");
            saveStatement.setString(10, product.getTotal()+"");
            saveStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Product update(Product product) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement updateStatement = connection.prepareStatement(
                        "UPDATE product SET prod_name= ?,prod_url= ?,prod_picture= ?,productionDate= ?,price= ? "
                        + ",currency=?,quantity=?,pID=?,total=? WHERE prod_code= ? ");) {
            updateStatement.setString(1, product.getProd_name());
            updateStatement.setString(2, product.getProd_url());
            updateStatement.setString(3, product.getProd_picture());
            updateStatement.setString(4, product.getProductionDate());
            updateStatement.setString(5, product.getPrice()+"");
            updateStatement.setString(6, product.getCurrency()+"");
            updateStatement.setString(7, product.getQuantity()+"");
            updateStatement.setString(8, product.getpID()+"");
            updateStatement.setString(9, product.getTotal()+"");
            updateStatement.setString(10, product.getProd_code()+"");
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return product;
    }

    @Override
    public void delete(Product product) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM product WHERE prod_code = ?");) {
            deleteStatement.setString(1, product.getProd_code()+"");
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Optional<Product> get(Integer prod_code) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (
            PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM Product WHERE prod_code=?")) {
            getStatement.setString(1, prod_code + "");
            ResultSet rs = getStatement.executeQuery();
            rs.next();
            return Optional.of(getResults(rs));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().getConnection();  
        try (
            PreparedStatement getAllStatement = connection.prepareStatement("SELECT * FROM Product")) {
            ResultSet rs = getAllStatement.executeQuery();
            while (rs.next()) {
                products.add(getResults(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }

    public List<Product> getAll(Integer pID) {
        List<Product> products = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().getConnection();  
        try (
            PreparedStatement getAllStatement = connection.prepareStatement("SELECT * FROM Product where pID =?")) {
            getAllStatement.setString(1, pID+"");
            ResultSet rs = getAllStatement.executeQuery();
            while (rs.next()) {
                products.add(getResults(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }

    @Override
    public Optional<Product> get(String prod_name) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM Product WHERE prod_name=?")) {
            getStatement.setString(1, prod_name + "");
            ResultSet rs = getStatement.executeQuery();
            rs.next();
            return Optional.of(getResults(rs));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    private static Product getResults(ResultSet r) {
        Product product = new Product();
        try {
            product.setProd_code(Integer.valueOf(r.getString("prod_code")));
            product.setCurrency(Integer.valueOf(r.getString("currency")));
            product.setPrice(Integer.valueOf(r.getString("price")));
            product.setQuantity(Integer.valueOf(r.getString("quantity")));
            product.setpID(Integer.valueOf(r.getString("pID")));
            product.setTotal(Integer.valueOf(r.getString("total")));
            product.setProd_name(r.getString("prod_name"));
            product.setProd_url(r.getString("prod_url"));
            product.setProd_picture(r.getString("prod_picture"));
            product.setProductionDate(r.getString("productionDate"));
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

