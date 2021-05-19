package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features"
        , glue = {"steps"}
        , plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , tags = "@Mandatory"
)
public class MandatoryRunner extends AbstractTestNGCucumberTests {

}
