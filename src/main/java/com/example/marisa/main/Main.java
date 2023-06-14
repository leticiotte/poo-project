package com.example.marisa.main;

import com.example.marisa.model.usecases.customer.UCCreateCustomer;
import com.example.marisa.model.usecases.customer.UCDeleteCustomer;
import com.example.marisa.model.usecases.customer.UCListCustomers;
import com.example.marisa.model.usecases.customer.UCUpdateCustomer;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.model.usecases.product.UCCreateProduct;
import com.example.marisa.model.usecases.product.UCDeleteProduct;
import com.example.marisa.model.usecases.product.UCListProducts;
import com.example.marisa.model.usecases.product.UCUpdateProduct;
import com.example.marisa.persistence.dao.DAOProduct;
import com.example.marisa.persistence.utils.DatabaseBuilder;
import com.example.marisa.view.WindowLoader;

public class Main {
    public static UCListCustomers ucListCustomers;
    public static UCDeleteCustomer ucDeleteCustomer;
    public static UCCreateCustomer ucCreateCustomer;
    public static UCUpdateCustomer ucUpdateCustomer;
    public static UCListProducts ucListProducts;
    public static UCDeleteProduct ucDeleteProduct;
    public static UCCreateProduct ucCreateProduct;
    public static UCUpdateProduct ucUpdateProduct;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        DAOCustomer daoCustomer = new DAOCustomer();
        ucListCustomers = new UCListCustomers(daoCustomer);
        ucDeleteCustomer = new UCDeleteCustomer(daoCustomer);
        ucCreateCustomer = new UCCreateCustomer(daoCustomer);
        ucUpdateCustomer = new UCUpdateCustomer(daoCustomer);

        DAOProduct daoProduct = new DAOProduct();
        ucListProducts = new UCListProducts(daoProduct);
        ucDeleteProduct = new UCDeleteProduct(daoProduct);
        ucCreateProduct = new UCCreateProduct(daoProduct);
        ucUpdateProduct = new UCUpdateProduct(daoProduct);
    }
}
