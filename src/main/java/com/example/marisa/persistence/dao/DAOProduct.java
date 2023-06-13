package com.example.marisa.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

public class DAOProduct implements DAO<Product, Integer> {

  @Override
  public void save(Product entity) {
    String sql = "INSERT INTO Product(name, sellPrice, buyPrice, quantity, facet, " +
        "category, minimumStock, size) " +
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      setEntityToPreparedStatement(entity, stmt);
      stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Product entity) {
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
  public void saveOrUpdate(Product entity) {
    Optional<Product> result = select(entity.getId());
    if (result.isPresent()) {
      update(entity);
    } else {
      save(entity);
    }
  }

  @Override
  public void delete(Integer key) {
    String sql = "UPDATE product SET active = false WHERE id = ?";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, key);
      stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Product> select(Integer key) {
    String sql = "SELECT * FROM product WHERE id = ?";
    List<Product> products = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, key);
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
  public List<Product> selectBy(String field, String value) {
    String sql = "SELECT * FROM product WHERE " + field + " = ?";
    List<Product> products = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setString(1, value);
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
            LocalDate.parse(rs.getString("creationDate")));
    return contact;
  }

  protected void setEntityToPreparedStatement(Product entity, PreparedStatement stmt)
      throws SQLException {
    stmt.setString(1, entity.getName());
    stmt.setDouble(2, entity.getSellPrice());
    stmt.setDouble(3, entity.getBuyPrice());
    stmt.setInt(4, entity.getQuantity());
    stmt.setString(5, entity.getFacet());
    stmt.setString(6, entity.getCategory());
    stmt.setInt(7, entity.getMinimumStock());
    stmt.setString(8, entity.getSize());
  }
}
