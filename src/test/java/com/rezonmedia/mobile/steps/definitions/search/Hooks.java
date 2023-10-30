package com.rezonmedia.mobile.steps.definitions.search;

import com.rezonmedia.mobile.util.BaseCore;
import io.cucumber.java.After;

public class Hooks {

    private final BaseCore baseCore;

    public Hooks(BaseCore baseCore){
        this.baseCore = baseCore;
    }

    @After("@cleanUp")
    public void afterScenario() {
            baseCore.getDriver().quit();
    }
}
