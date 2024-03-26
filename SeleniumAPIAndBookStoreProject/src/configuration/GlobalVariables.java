package configuration;

import org.openqa.selenium.WebDriver;

import HomePagePOM.HomePagePOM;

public class GlobalVariables{
	
	public static final String projectpath = "C:\\Users\\raghu\\eclipse-workspace\\BookStore\\";

	
	public static final String resultPath = "C:\\Results\\";
	public static final String browserfilepath  = "C:\\Users\\raghu\\eclipse-workspace\\BookStore\\Browser Driver\\";
	

	public static final String featurefilepath = projectpath + "\\Test Scenarios and Test Cases\\";
	
	public static final String APIFeatureFilePath = featurefilepath + "Api.feature";
	public static final String BookStoreFeatureFilePath = featurefilepath + "BookStore.feature";
	public static final String targetPath = projectpath + "\\target\\";
	public static String propertiesfilename = null;
	public static String TestDatapropertiesfilepath = projectpath + "\\src\\TestData_Files\\";
	public static HomePagePOM HomePagePOM = null;
	
	public static WebDriver driver = null;
	
	
}