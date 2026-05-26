package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.utils.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        // Driver akan diinisialisasi saat pertama kali dipanggil oleh DriverManager
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}