package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features"
        , glue = {"steps"}
        ,tags = "@Mandatory"
        , plugin = {"pretty"}
)
public class MandatoryRunner extends AbstractTestNGCucumberTests {
}
