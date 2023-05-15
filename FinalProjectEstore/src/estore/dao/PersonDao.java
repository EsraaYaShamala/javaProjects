package estore.dao;

import estore.DatabaseManager;
import estore.models.Person;
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

public class PersonDao implements Dao<Person> {

    private static PersonDao instance;

    private PersonDao() {

    }

    public static PersonDao getInstance() {
        if (instance == null) {
            instance = new PersonDao();
        }
        return instance;
    }

    private static Person getResults(ResultSet result) {
        Person person = new Person();
        try {
            person.setpID(Integer.valueOf(result.getString("pID")));
            person.setpName(result.getString("pName"));
            person.setpClassification(result.getString("pClassification"));
            person.setNewPassword(result.getString("newPassword"));
            person.setOldPassword(result.getString("oldPassword"));
            person.setpPassword(result.getString("pPassword"));
            return person;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Optional<Person> get(Integer pID) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (
            PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM person WHERE pID=?")) {
            getStatement.setString(1, pID + "");
            ResultSet rs = getStatement.executeQuery();
            rs.next();
            return Optional.of(getResults(rs));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        List<Person> person = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().getConnection();
        try (
                 PreparedStatement getAllStatement = connection.prepareStatement("SELECT * FROM person")) {
            ResultSet rs = getAllStatement.executeQuery();
            while (rs.next()) {
                person.add(getResults(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return person;
    }

    @Override
    public void save(Person person) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement saveStatement = connection.prepareStatement(
                "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)")) {
            saveStatement.setString(1, person.getpID() + "");
            saveStatement.setString(2, person.getpName());
            saveStatement.setString(3, person.getpClassification());
            saveStatement.setString(4, person.getpPassword());
            saveStatement.setString(5, person.getOldPassword());
            saveStatement.setString(6, person.getNewPassword());

            saveStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Person update(Person person) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE person SET pName= ?,pClassification= ?,pPassword= ?,oldPassword= ? "
                + ",newPassword=? WHERE pID= ? ");) {
            updateStatement.setString(1, person.getpName());
            updateStatement.setString(2, person.getpClassification());
            updateStatement.setString(3, person.getpPassword());
            updateStatement.setString(4, person.getOldPassword());
            updateStatement.setString(5, person.getNewPassword());
            updateStatement.setString(6, person.getpID() + "");
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return person;
    }

    @Override
    public void delete(Person person) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM person WHERE pID = ?");) {
            deleteStatement.setString(1, person.getpID() + "");
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Optional<Person> get(String pName) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        try ( PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM person WHERE pName=?")) {
            getStatement.setString(1, pName + "");
            ResultSet rs = getStatement.executeQuery();
            rs.next();
            return Optional.of(getResults(rs));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }
}
