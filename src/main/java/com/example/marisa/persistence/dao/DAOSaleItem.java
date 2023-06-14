package com.example.marisa.persistence.dao;

import com.example.marisa.model.entities.Product;
import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOSaleItem implements DAO<SaleItem, Integer> {

  @Override
  public void save(SaleItem entity) {

  }

  @Override
  public void update(SaleItem entity) {

  }

  @Override
  public void saveOrUpdate(SaleItem entity) {
  }

  @Override
  public void delete(Integer key) {
  }

  @Override
  public Optional<SaleItem> select(Integer key) {
    return Optional.empty();
  }

  @Override
  public List<SaleItem> selectAll() {
    String sql = "SELECT * FROM sale_item";
    List<SaleItem> saleItems = new ArrayList<>();

    try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        SaleItem saleItem = getEntityFromResultSet(rs);
        saleItems.add(saleItem);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return saleItems;
  }

  @Override
  public List<SaleItem> selectBy(String field, String value) {
    String sql = "SELECT * FROM product WHERE " + field + " = ? AND active = 1";
    List<SaleItem> saleItems = new ArrayList<>();

    return saleItems;
  }

  public SaleItem getEntityFromResultSet(ResultSet rs) throws SQLException {
    DAOProduct daoProduct = new DAOProduct();
    Optional<Product> product = daoProduct.select(rs.getInt("productId"));
    if (product.isEmpty()) throw new SQLException("Produto não encontrado.");

    SaleItem saleItem = new SaleItem(
        product.get(),
        rs.getInt("quantity"),
        rs.getFloat("discount"),
        rs.getFloat("payablePrice"),
        rs.getFloat("discountValue")
    );
    return saleItem;
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
    stmt.setString(9, entity.getCreationDate().toString());
  }
}
