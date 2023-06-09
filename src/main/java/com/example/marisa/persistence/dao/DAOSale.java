package com.example.marisa.persistence.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

public class DAOSale implements DAO<Sale, Integer> {

  @Override
  public void save(Sale entity) {
    // TODO Auto-generated method stub

    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void update(Sale entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void saveOrUpdate(Sale entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
  }

  @Override
  public void delete(Integer key) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Optional<Sale> select(Integer key) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'select'");
  }

  @Override
  public List<Sale> selectAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }

  public void updateSaleStatus(Integer saleId, String status){
    String sql = "UPDATE sale SET saleStatus = ? WHERE id = ?";

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setString(1, status);
      stmt.setInt(2, saleId);

      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Sale> selectCashierSales(int cashierId, Date date) {
    String sql = "SELECT * FROM sale WHERE cashier_id = ? AND date >= ?";
    List<Sale> sales = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      stmt.setInt(1, cashierId);
      stmt.setDate(2, date);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Sale sale = getEntityFromResultSet(rs);
        sales.add(sale);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sales;
  }

  @Override
  public Sale getEntityFromResultSet(ResultSet rs) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityFromResultSet'");
  }

  @Override
  public List<Sale> selectBy(String field, String value) {
    throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
  }
  public List<Sale> selectSalesByProduct(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }

  public Integer selectLastIdGenerated() {
    String sql = "SELECT last_insert_rowid() AS last_id FROM sale;";
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
