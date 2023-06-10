package com.example.marisa.persistence.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {
    void save(T entity);

    void update(T entity);

    void saveOrUpdate(T entity);

    void delete(K key);

    Optional<T> select(K key);

    List<T> selectAll();

    List<T> selectBy(String field, String value);

    T getEntityFromResultSet(ResultSet rs) throws SQLException;
}
