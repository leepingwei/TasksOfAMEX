package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Might need your own path here
        features = {"/Users/lee/Desktop/AMEXTasks/AMEXUITask/src/test/java/FeatureFiles"},
        glue = {"StepDefinitions"},
        plugin = {
                "pretty",
                "html:Reports/Html_report.html",
                "json:Reports/JSON_report.json",
                "junit:Reports/Junit_report.xml"})
public class TestRunner {
}
