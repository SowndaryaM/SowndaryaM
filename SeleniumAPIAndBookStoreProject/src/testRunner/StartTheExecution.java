package testRunner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runner.*;


import com.cucumber.listener.Reporter;

import configuration.GlobalVariables;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
features= {GlobalVariables.APIFeatureFilePath,GlobalVariables.BookStoreFeatureFilePath},
plugin = {"pretty","html:target/cucumber-reports",
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
        "junit:target/cucumber-reports/Cucumber.xml",
        "json:target/cucumber.json",
        "rerun:target/rerun.txt"},
glue = "stepDefinitions")

public class StartTheExecution {

	@AfterClass
	public static void writeExtentReport() {
		
		//Reporter.loadXMLConfig(new File(GlobalVariables.extentconfigFile));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "4.9.1");
		Reporter.setSystemInfo("Maven", "3.9.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_371");
	}

}