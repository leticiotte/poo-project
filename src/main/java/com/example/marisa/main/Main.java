package com.example.marisa.main;

import com.example.marisa.persistence.utils.DatabaseBuilder;
import com.example.marisa.view.WindowLoader;

public class Main {


    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    public static void main(String[] args) {
        setupDatabase();
        WindowLoader.main(args);
    }
}
