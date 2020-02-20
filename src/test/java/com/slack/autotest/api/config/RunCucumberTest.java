package com.slack.autotest.api.config;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumberReports"},
glue = "com.slack.autotest.api.steps",
features = "src/test/resources/features")
public class RunCucumberTest {
}
