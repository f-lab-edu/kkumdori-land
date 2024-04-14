package org.example.kkumdoriland.cucumber.steps;

import io.cucumber.java8.En;
import org.example.kkumdoriland.utils.DatabaseCleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class BeforeStepDef implements En  {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    public BeforeStepDef() {
        Before(() -> {
            databaseCleanup.execute();
        });
    }

}
