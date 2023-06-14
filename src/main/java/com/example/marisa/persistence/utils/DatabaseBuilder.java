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
            // TABLES
            stmt.addBatch(createCashierTableSql());
            stmt.addBatch(createProductTableSql());
            stmt.addBatch(createCustomerTableSql());
            stmt.addBatch(createSaleTableSql());
            stmt.addBatch(createSaleItemTableSql());
            stmt.addBatch(createChargeBackTableSql());
            stmt.addBatch(createSaleChargeBackTableSql());

            // TRIGGERS
            stmt.addBatch(insertSaleItemTrigger());
            stmt.addBatch(updateSaleItemTrigger());
            stmt.addBatch(deleteSaleItemTrigger());
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

        builder.append("CREATE TABLE sale (");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append("cashierId INTEGER, ");
        builder.append("customerCpf TEXT, ");
        builder.append("date TEXT, ");
        builder.append("totalPayablePrice REAL, ");
        builder.append("totalDiscount REAL, ");
        builder.append("paymentType TEXT, ");
        builder.append("nf TEXT, ");
        builder.append("saleStatus TEXT, ");
        builder.append("FOREIGN KEY (cashierId) REFERENCES cashier (id), ");
        builder.append("FOREIGN KEY (customerCpf) REFERENCES customer (cpf)");
        builder.append(");");

        System.out.println(builder);
        return builder.toString();
    }

    private String createSaleItemTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE sale_item (");
        builder.append("saleId INTEGER, ");
        builder.append("productId INTEGER, ");
        builder.append("productName TEXT, ");
        builder.append("productSellPrice REAL, ");
        builder.append("productBuyPrice REAL, ");
        builder.append("quantity INTEGER, ");
        builder.append("payablePrice REAL, ");
        builder.append("discountValue REAL, ");
        builder.append("refund INTEGER DEFAULT 0, ");
        builder.append("FOREIGN KEY (saleId) REFERENCES sale(id), ");
        builder.append("FOREIGN KEY (productId) REFERENCES product(id) ");
        builder.append(");");

        System.out.println(builder);
        return builder.toString();
    }

    private String insertSaleItemTrigger(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TRIGGER update_sale_after_insert ");
        builder.append("AFTER INSERT ON sale_item ");
        builder.append("FOR EACH ROW ");
        builder.append("BEGIN ");
        builder.append("UPDATE sale ");
        builder.append("SET totalPayablePrice = totalPayablePrice + NEW.payablePrice, ");
        builder.append("totalDiscount = totalDiscount + NEW.discountValue ");
        builder.append("WHERE id = NEW.saleId; ");
        builder.append("UPDATE product ");
        builder.append("SET quantity = quantity - NEW.quantity ");
        builder.append("WHERE id = NEW.productId; ");
        builder.append("END;");

        System.out.println(builder);
        return builder.toString();
    }

    private String updateSaleItemTrigger(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TRIGGER update_sale_after_update ");
        builder.append("AFTER UPDATE ON sale_item ");
        builder.append("FOR EACH ROW ");
        builder.append("WHEN NEW.refund = 1 ");
        builder.append("BEGIN ");
        builder.append("UPDATE sale ");
        builder.append("SET totalPayablePrice = totalPayablePrice - NEW.payablePrice, ");
        builder.append("totalDiscount = totalDiscount - NEW.discountValue ");
        builder.append("WHERE id = NEW.saleId; ");
        builder.append("UPDATE product ");
        builder.append("SET quantity = quantity + NEW.quantity ");
        builder.append("WHERE id = NEW.productId; ");
        builder.append("END;");

        System.out.println(builder);
        return builder.toString();
    }

    private String deleteSaleItemTrigger(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TRIGGER update_sale_after_delete ");
        builder.append("AFTER DELETE ON sale_item ");
        builder.append("FOR EACH ROW ");
        builder.append("BEGIN ");
        builder.append("UPDATE sale ");
        builder.append("SET totalPayablePrice = totalPayablePrice - OLD.payablePrice, ");
        builder.append("totalDiscount = totalDiscount - OLD.discountValue ");
        builder.append("WHERE id = OLD.saleId; ");
        builder.append("UPDATE product ");
        builder.append("SET quantity = quantity + OLD.quantity ");
        builder.append("WHERE id = OLD.productId; ");
        builder.append("END;");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCashierTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE cashier (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("openingBalance REAL NOT NULL, \n");
        builder.append("finalBalance REAL NULL, \n");
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
