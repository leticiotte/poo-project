package com.example.marisa.persistence.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
        if (!isDatabaseAvailable()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseAvailable() {
        return Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement stmt = DatabaseConnectionFactory.createStatement()) {
            stmt.addBatch(createCashierTableSql());
            stmt.addBatch(createProductTableSql());
            stmt.addBatch(createChargeBackTableSql());
            stmt.addBatch(createSaleChargeBackTableSql());
            stmt.addBatch(createCustomerTableSql());
            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createProductTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Product (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("sellPrice REAL NOT NULL, \n");
        builder.append("buyPrice REAL NOT NULL, \n");
        builder.append("quantity INTEGER, \n");
        builder.append("facet TEXT, \n");
        builder.append("size TEXT NOT NULL, \n");
        builder.append("category TEXT NOT NULL, \n");
        builder.append("minimumStock INTEGER, \n");
        builder.append("creationDate TEXT, \n");
        builder.append("active TEXT DEFAULT 'true' NOT NULL");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCustomerTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE customer (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL, \n");
        builder.append("phone TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("number INTEGER NOT NULL, \n");
        builder.append("street TEXT NOT NULL, \n");
        builder.append("complement TEXT NULL, \n");
        builder.append("city TEXT NOT NULL, \n");
        builder.append("country TEXT NOT NULL, \n");
        builder.append("zipcode TEXT NOT NULL");
        builder.append("active TEXT DEFAULT 'true' NOT NULL");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCashierTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE cashier (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("openingBalance REAL NOT NULL, \n");
        builder.append("finalBalance REAL NOT NULL, \n");
        builder.append("status TEXT NOT NULL");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createChargeBackTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE charge_back (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("date TEXT, \n");
        builder.append("sale_id INTEGER, \n");
        builder.append("FOREIGN KEY (sale_id) REFERENCES sale (id)");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createSaleChargeBackTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE sale_charge_back (\n");
        builder.append("sale_id INTEGER, \n");
        builder.append("charge_back_id INTEGER, \n");
        builder.append("PRIMARY KEY (charge_back_id, sale_id), \n");
        builder.append("FOREIGN KEY(sale_id) REFERENCES sale(id), \n");
        builder.append("FOREIGN KEY(charge_back_id) REFERENCES charge_back(id), \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }
}
