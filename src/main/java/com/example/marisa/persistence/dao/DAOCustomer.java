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

public class DAOCustomer implements DAO<Customer, Integer> {

  @Override
  public void save(Customer entity) {
    String sql = "INSERT INTO Customer(name, cpf, phone, email, status, number, street, complement, city, country, zipcode) "
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      setEntityToPreparedStatement(entity, stmt);
      stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Customer entity) {
    String sql = "UPDATE Product SET name = ?, sellPrice = ?, buyPrice = ?, quantity = ?, facet = ?, " +
        "category = ?, minimumStock = ?, size = ? WHERE id = ?";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      setEntityToPreparedStatement(entity, stmt);
      stmt.setInt(9, entity.getId());
      stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveOrUpdate(Customer entity) {
    Optional<Customer> result = select(entity.getId());
    if (result.isPresent()) {
      update(entity);
    } else {
      save(entity);
    }
  }

  @Override
  public void delete(Integer key) {
    String sql = "UPDATE customer SET active = FALSE WHERE id = ?";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, key);
      stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Customer> select(Integer key) {
    String sql = "SELECT * FROM customer WHERE id = ?";
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

    return customers.stream().findFirst();
  }

  @Override
  public List<Customer> selectAll() {
    String sql = "SELECT * FROM customer";
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
  public List<Customer> selectBy(String field, Object value) {
    String sql = "SELECT * FROM customer WHERE " + field + " = ?";
    List<Customer> customers = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setString(1, field);
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
    Customer contact = new Customer(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("cpf"),
        rs.getString("complement"),
        rs.getString("street"),
        rs.getString("zipcode"),
        rs.getInt("number"),
        rs.getString("city"),
        rs.getString("country"),
        rs.getString("phone"),
        rs.getString("email"),
        rs.getString("status"));
    return contact;
  }

  protected void setEntityToPreparedStatement(Customer entity, PreparedStatement stmt)
      throws SQLException {
    stmt.setInt(1, entity.getId());
    stmt.setString(2, entity.getName());
    stmt.setString(3, entity.getCpf());
    stmt.setString(4, entity.getPhone());
    stmt.setString(5, entity.getEmail());
    stmt.setString(6, entity.getStatus());
    stmt.setInt(7, entity.getNumber());
    stmt.setString(8, entity.getStreet());
    stmt.setString(8, entity.getComplement());
    stmt.setString(8, entity.getCity());
    stmt.setString(8, entity.getCountry());
    stmt.setString(8, entity.getZipcode());
  }
}
