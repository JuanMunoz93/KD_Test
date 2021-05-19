package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features"
        , glue = {"steps"}
        ,tags = "@Newsletter"
        , plugin = {"pretty"}
)
public class NewsletterRegressionRunner extends AbstractTestNGCucumberTests {
}
