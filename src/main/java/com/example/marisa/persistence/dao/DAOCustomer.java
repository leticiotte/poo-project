package com.example.marisa.persistence.dao;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCustomer implements DAO<Customer, String> {

  @Override
  public void save(Customer entity) {
    String sql = "INSERT INTO Customer(name, cpf, phone, email, status, number, street, complement, city, country, zipcode) "
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      setEntityToPreparedStatement(entity, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Customer entity) {
    String sql = "UPDATE Customer SET name = ?, phone = ?, email = ?, status = ?, number = ?, street = ?, " +
        "complement = ?, city = ?, country = ?, zipcode = ? WHERE cpf = ? AND active = 1";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      setEntityToPreparedStatementUpdate(entity, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveOrUpdate(Customer entity) {
    Optional<Customer> result = select(entity.getCpf());
    if (result.isPresent()) {
      update(entity);
    } else {
      save(entity);
    }
  }

  @Override
  public void delete(String key) {
    String sql = "UPDATE customer SET active = 0 WHERE cpf = ?";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setString(1, key);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Customer> select(String key) {
    String sql = "SELECT * FROM customer WHERE cpf = ? AND active = 1";
    List<Customer> customers = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setString(1, key);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Customer customer = getEntityFromResultSet(rs);
        customers.add(customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customers.stream().findFirst();
  }

  @Override
  public List<Customer> selectAll() {
    String sql = "SELECT * FROM customer WHERE active = 1";
    List<Customer> customers = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Customer customer = getEntityFromResultSet(rs);
        customers.add(customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }

  @Override
  public List<Customer> selectBy(String field, String value) {
    String sql;
    if(field.equals("name")){
      sql = "SELECT * FROM customer WHERE UPPER(name) LIKE '%"+ value +"%' AND active = 1";
    }else{
      sql = "SELECT * FROM customer WHERE " + field + " = ? AND active = 1";
    }
    List<Customer> customers = new ArrayList<>();
    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Customer customer = getEntityFromResultSet(rs);
        customers.add(customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }

  public Customer getEntityFromResultSet(ResultSet rs) throws SQLException {
    return new Customer(
        rs.getString("name"),
        rs.getString("cpf"),
        rs.getString("phone"),
        rs.getString("email"),
        rs.getString("status"),
        rs.getInt("number"),
        rs.getString("street"),
        rs.getString("complement"),
        rs.getString("city"),
        rs.getString("country"),
        rs.getString("zipcode"));
  }

  protected void setEntityToPreparedStatement(Customer entity, PreparedStatement stmt) throws SQLException {
    stmt.setString(1, entity.getName());
    stmt.setString(2, entity.getCpf());
    stmt.setString(3, entity.getPhone());
    stmt.setString(4, entity.getEmail());
    stmt.setString(5, "created");
    stmt.setInt(6, entity.getNumber());
    stmt.setString(7, entity.getStreet());
    stmt.setString(8, entity.getComplement());
    stmt.setString(9, entity.getCity());
    stmt.setString(10, entity.getCountry());
    stmt.setString(11, entity.getZipcode());
  }

  protected void setEntityToPreparedStatementUpdate(Customer entity, PreparedStatement stmt) throws SQLException {
    stmt.setString(1, entity.getName());
    stmt.setString(2, entity.getPhone());
    stmt.setString(3, entity.getEmail());
    stmt.setString(4, "updated");
    stmt.setInt(5, entity.getNumber());
    stmt.setString(6, entity.getStreet());
    stmt.setString(7, entity.getComplement());
    stmt.setString(8, entity.getCity());
    stmt.setString(9, entity.getCountry());
    stmt.setString(10, entity.getZipcode());
    stmt.setString(11, entity.getCpf());
  }

}
