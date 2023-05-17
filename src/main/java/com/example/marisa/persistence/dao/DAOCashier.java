package com.example.marisa.persistence.dao;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DAOCashier implements DAO<Cashier, Integer> {

    @Override
    public Optional<Cashier> select(Integer key) {
        String sql = "SELECT id, openingBalance, finalBalance, status FROM cashier WHERE id = ?";
        Cashier cashier = null;

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            cashier = getEntityFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cashier);
    }

    @Override
    public void save(Cashier cashier) {
        String sql = "INSERT INTO cashier (openingBalance, status) VALUES (?,?)";

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setFloat(1, cashier.getOpeningBalance());
            stmt.setString(2, cashier.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cashier cashier) {
        String sql = "UPDATE cashier SET finalBalance = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setFloat(1, cashier.getFinalBalance());
            stmt.setString(2, cashier.getStatus());
            stmt.setInt(3, cashier.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cashier getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Cashier(
                rs.getInt("id"),
                rs.getFloat("openingBalance"),
                rs.getFloat("finalBalance"),
                rs.getString("status"));
    }

    @Override
    public void saveOrUpdate(Cashier entity) {
        throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Cashier> selectAll() {
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public List<Cashier> selectBy(String field, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }
}