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

  @Override
  public List<Sale> selectBy(String field, Object value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
  }

  @Override
  public Sale getEntityFromResultSet(ResultSet rs) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityFromResultSet'");
  }

  public List<Sale> selectSalesByProduct(Product product) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }
}
