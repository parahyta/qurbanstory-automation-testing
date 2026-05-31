package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import org.example.utils.DriverManager;

public class CommonSteps {

    @Given("user accesses the Qurban Story website")
    public void userAccessesTheQurbanStoryWebsite() {
        DriverManager.getDriver()
                .get("https://qurban-story.vercel.app");
    }
}