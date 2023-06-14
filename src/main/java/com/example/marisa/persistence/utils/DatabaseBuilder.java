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
            stmt.addBatch(createCustomerTableSql());
            stmt.addBatch(createSaleTableSql());
            stmt.addBatch(createSaleItemTableSql());
//            stmt.addBatch(createSaleItemTrigger());
            stmt.addBatch(createChargeBackTableSql());
            stmt.addBatch(createSaleChargeBackTableSql());
            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createProductTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE product (\n");
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
        builder.append("active INTEGER DEFAULT 1 NOT NULL");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCustomerTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE customer (\n");
        builder.append("cpf TEXT PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("phone TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("number INTEGER NOT NULL, \n");
        builder.append("street TEXT NOT NULL, \n");
        builder.append("complement TEXT NULL, \n");
        builder.append("city TEXT NOT NULL, \n");
        builder.append("country TEXT NOT NULL, \n");
        builder.append("zipcode TEXT NOT NULL, \n");
        builder.append("active INTEGER DEFAULT 1 NOT NULL");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createSaleTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE sale (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cashierId INTEGER, \n");
        builder.append("customerCpf TEXT, \n");
        builder.append("date TEXT, \n");
        builder.append("totalPayablePrice REAL, \n");
        builder.append("totalDiscount REAL, \n");
        builder.append("paymentType TEXT, \n");
        builder.append("nf TEXT, \n");
        builder.append("saleStatus TEXT, \n");
        builder.append("FOREIGN KEY (cashierId) REFERENCES cashier (id), \n");
        builder.append("FOREIGN KEY (customerCpf) REFERENCES customer (cpf) \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createSaleItemTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE sale_item (");
        builder.append("saleId INTEGER,");
        builder.append("productId INTEGER,");
        builder.append("productName TEXT,");
        builder.append("productSellPrice REAL,");
        builder.append("productBuyPrice REAL,");
        builder.append("quantity INTEGER,");
        builder.append("payablePrice REAL,");
        builder.append("discountValue REAL,");
        builder.append("refund INTEGER DEFAULT 0,");
        builder.append("FOREIGN KEY (saleId) REFERENCES sale(id),");
        builder.append("FOREIGN KEY (productId) REFERENCES product(id)");
        builder.append(");");

        System.out.println(builder);
        return builder.toString();
    }

//    private String createSaleItemTrigger(){
//        // Trigger para atualizar o valor de totalPayablePrice e totalDiscount
//        // da Sale a cada inserção ou remoção de item na compra
//        // além de atualizar o estoque na tabela Product
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("CREATE TRIGGER update_sale_totals ");
//        builder.append("AFTER INSERT OR DELETE ON sale_item ");
//        builder.append("FOR EACH ROW ");
//        builder.append("BEGIN ");
//        builder.append("UPDATE sale ");
//        builder.append("SET totalPayablePrice = (");
//        builder.append("SELECT SUM(payablePrice) ");
//        builder.append("FROM sale_item ");
//        builder.append("WHERE saleId = NEW.saleId");
//        builder.append("), ");
//        builder.append("totalDiscount = (");
//        builder.append("SELECT SUM(discountValue) ");
//        builder.append("FROM sale_item ");
//        builder.append("WHERE saleId = NEW.saleId");
//        builder.append("); ");
//        builder.append("END;");
//
//        System.out.println(builder);
//        return builder.toString();
//    }

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
        builder.append("FOREIGN KEY(charge_back_id) REFERENCES charge_back(id) \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }
}
