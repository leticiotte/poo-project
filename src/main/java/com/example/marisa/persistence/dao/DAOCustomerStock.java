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

public class DAOCustomerStock implements DAO<Customer, Integer> {

  @Override
  public void save(Customer entity) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void update(Customer entity) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void saveOrUpdate(Customer entity) {
    throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
  }

  @Override
  public void delete(Integer key) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Optional<Customer> select(Integer key) {
    String sql = "SELECT * FROM product WHERE id = ?";
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
    throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
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
}
