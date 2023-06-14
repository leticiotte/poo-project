package com.example.marisa.persistence.dao;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DAOSaleItem {

  public void save(SaleItem saleItem) {
    String sql = "INSERT INTO sale_item(saleId, productId, productName, productSellPrice, productBuyPrice, " +
            "quantity, payablePrice, discountValue) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, saleItem.getSaleId());
      stmt.setInt(2, saleItem.getProduct().getId());
      stmt.setString(3, saleItem.getProduct().getName());
      stmt.setDouble(4, saleItem.getProduct().getSellPrice());
      stmt.setDouble(5, saleItem.getProduct().getBuyPrice());
      stmt.setInt(6, saleItem.getQuantity());
      stmt.setDouble(7, saleItem.getPayablePrice());
      stmt.setDouble(8, saleItem.getDiscountValue());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(Integer saleId, Integer productId) {
    String sql = "DELETE FROM sale_id WHERE saleId = ? AND productId = ?";
    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, saleId);
      stmt.setInt(2, productId);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Optional<SaleItem> select(Integer saleId, Integer productId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'select'");
  }

  public List<SaleItem> selectAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }

  public Sale getEntityFromResultSet(ResultSet rs) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityFromResultSet'");
  }

  public List<Sale> selectSalesItensFromSale(Integer saleId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }

  public Integer selectLastIdGenerated() {
    String sql = "SELECT last_insert_rowid() AS last_id FROM sale_item;";
    Integer id = null;
    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      id = rs.getInt("last_id");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }
}
