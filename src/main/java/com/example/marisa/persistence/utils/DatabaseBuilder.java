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
            stmt.addBatch(createAddressTableSql());
            stmt.addBatch(createCashierTableSql());
            stmt.addBatch(createProductTabel());
            stmt.addBatch(createChargeBackTableSql());
            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createAddressTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Address (\n");
        builder.append("id INTEGER PRIMARY KEY, \n");
        builder.append("street TEXT NOT NULL, \n");
        builder.append("number INTEGER, \n");
        builder.append("city TEXT NOT NULL, \n");
        builder.append("state TEXT NOT NULL, \n");
        builder.append("zipCode TEXT, ");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
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
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCashierTableSql(){
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

    private String createChargeBackTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE charge_back (\n");
        builder.append("id INTEGER PRIMARY KEY, \n");
        builder.append("date DATE, \n");
        builder.append("sale_id INTEGER, \n");
        builder.append("FOREIGN KEY (sale_id) REFERENCES sale (id)");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
