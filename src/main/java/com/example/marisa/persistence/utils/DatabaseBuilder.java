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
            stmt.addBatch(createProductTabel());
            stmt.addBatch(createCustomerTableSql());
            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createProductTabel() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Product (\n");
        builder.append("id INTEGER PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("sellPrice NUMBER NOT NULL, \n");
        builder.append("buyPrice NUMBER NOT NULL, \n");
        builder.append("quantity INTEGER, \n");
        builder.append("facet TEXT, \n");
        builder.append("size TEXT NOT NULL, \n");
        builder.append("category TEXT NOT NULL, \n");
        builder.append("minimumStock INTEGER, \n");
        builder.append("creationDate DATE, \n");
        builder.append("active BOOLEAN NOT NULL DEFAULT true");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCustomerTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE customer (\n");
        builder.append("id INTEGER PRIMARY KEY, \n");
        builder.append("name VARCHAR(255) NOT NULL, \n");
        builder.append("cpf VARCHAR(11) NOT NULL, \n");
        builder.append("phone VARCHAR(20) NOT NULL, \n");
        builder.append("email VARCHAR(255) NOT NULL, \n");
        builder.append("status VARCHAR(20) NOT NULL, \n");
        builder.append("number INTEGER NOT NULL, \n");
        builder.append("street VARCHAR(255) NOT NULL, \n");
        builder.append("complement VARCHAR(255) NULL, \n");
        builder.append("city VARCHAR(255) NOT NULL, \n");
        builder.append("country VARCHAR(255) NOT NULL, \n");
        builder.append("zipcode VARCHAR(20) NOT NULL");
        builder.append("active BOOLEAN NOT NULL DEFAULT true");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCashierTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE cashier (\n");
        builder.append("id INTEGER PRIMARY KEY, \n");
        builder.append("openingBalance numeric(6,2) NOT NULL, \n");
        builder.append("finalBalance numeric(6,2) NULL, \n");
        builder.append("status TEXT NOT NULL");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
