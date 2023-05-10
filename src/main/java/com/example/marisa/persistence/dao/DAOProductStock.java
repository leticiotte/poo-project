package com.example.marisa.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

public class DAOProductStock implements DAO<Product, Integer> {

  @Override
  public void save(Product entity) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void update(Product entity) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void saveOrUpdate(Product entity) {
    throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
  }

  @Override
  public void delete(Integer key) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Optional<Product> select(Integer key) {
    String sql = "SELECT * FROM product WHERE id = ?";
    List<Product> products = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Product product = getEntityFromResultSet(rs);
        products.add(product);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return products.stream().findFirst();
  }

  @Override
  public List<Product> selectAll() {
    String sql = "SELECT * FROM product";
    List<Product> products = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Product product = getEntityFromResultSet(rs);
        products.add(product);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  @Override
  public List<Product> selectBy(String field, Object value) {
    throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
  }

  public Product getEntityFromResultSet(ResultSet rs) throws SQLException {
    Product contact = new Product(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getFloat("sellPrice"),
        rs.getFloat("buyPrice"),
        rs.getInt("quantity"),
        rs.getString("size"),
        rs.getString("facet"),
        rs.getString("category"),
        rs.getInt("minimumStock"),
        rs.getString("creationDate"));
    return contact;
  }
}
