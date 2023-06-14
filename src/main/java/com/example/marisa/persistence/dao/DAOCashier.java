package com.example.marisa.persistence.dao;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.utils.DAO;
import com.example.marisa.persistence.utils.DatabaseConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCashier implements DAO<Cashier, Integer> {

    @Override
    public Optional<Cashier> select(Integer id) {
        String sql = "SELECT id, openingBalance, finalBalance, status FROM cashier WHERE id = ?";
        Cashier cashier = null;

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            cashier = getEntityFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cashier);
    }

    @Override
    public List<Cashier> selectAll() {
        String sql = "SELECT * FROM cashier";
        List<Cashier> cashiers = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cashier cashier = getEntityFromResultSet(rs);
                cashiers.add(cashier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashiers;
    }

    @Override
    public void save(Cashier cashier) {
        String sql = "INSERT INTO cashier (id, openingBalance, status) VALUES (?,?,?)";

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDouble(1, cashier.getId());
            stmt.setDouble(2, cashier.getOpeningBalance());
            stmt.setString(3, cashier.getStatus().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open(Cashier cashier) {
        String sql = "UPDATE cashier SET openingBalance = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDouble(1, cashier.getOpeningBalance());
            stmt.setString(2, cashier.getStatus().name());
            stmt.setInt(3, cashier.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Cashier cashier) {
        String sql = "UPDATE cashier SET finalBalance = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDouble(1, cashier.getFinalBalance());
            stmt.setString(2, cashier.getStatus().name());
            stmt.setInt(3, cashier.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cashier selectClosedCashier() {
        String sql = "SELECT id, openingBalance, finalBalance, status FROM cashier WHERE status = 'CLOSED' LIMIT 1";
        Cashier cashier = null;

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) cashier = getEntityFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("Estou no return");
        return cashier;
    }

    public Cashier selectById(Integer id){
        String sql = "SELECT id, openingBalance, finalBalance, status FROM cashier WHERE id = ?";
        Cashier cashier = new Cashier();

        try (PreparedStatement stmt = DatabaseConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) cashier = getEntityFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashier;
    }

    public Cashier getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Cashier(
                rs.getInt("id"),
                rs.getDouble("openingBalance"),
                rs.getDouble("finalBalance"),
                rs.getString("status"));
    }

    @Override
    public void saveOrUpdate(Cashier entity) {
        throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
    }

    @Override
    public void update(Cashier entity) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Cashier> selectBy(String field, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }
}
