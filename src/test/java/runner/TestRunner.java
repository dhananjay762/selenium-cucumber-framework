package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions; // Or io.cucumber.testng.CucumberOptions


@CucumberOptions(
		features = {
				"src/test/resources/feature",
//				"src/test/resources/feature/Login.feature",
//				"src/test/resources/feature/OrderProduct.feature"
		},
		glue = {
				"utility",
				"stepDef"
		},
//		plugin = { "pretty", "html:target/cucumber-reports/report.html"},
		plugin = { 
				"pretty", 
				"html:target/cucumber-reports/report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
//		tags = "@Smoke",
		dryRun = false,
		monochrome = true
	)

public class TestRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
}
