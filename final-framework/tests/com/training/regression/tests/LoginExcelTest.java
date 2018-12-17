package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import com.training.bean.LoginBean;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/**
 * 
 * @author Naveen
 * @see program to show working of parameterization with excel 
 */

@RunWith(Parameterized.class)
public class LoginExcelTest {
	private WebDriver driver; 
	private String baseUrl; 
	private LoginPOM loginPOM; 
	private static Properties properties; 
	private ScreenShot screenShot; 
	static ApachePOIExcelRead excelRead; 
	static String filePath; 


	// in this class have not used String variable 
	// rather the bean object to show different ways of working 
	private LoginBean loginBean; 
	
	public LoginExcelTest(LoginBean loginBean) {
		this.loginBean = loginBean; 
	}
	

	// jUnit will invoke the static method which is annotated with Parameters 
	// and the array of value which are generated from this method 
	// will be injected to contractors 
	@Parameters
	public static List<LoginBean> data(){
		List<LoginBean> loginList = new ArrayList<LoginBean>(); 

		// return List of Array of Object 
		
		// the values here can be hard coded 
		// or pull from database or excel sheet 
		filePath = "C:/Users/Naveen/Desktop/Testing.xlsx"; 
		
		List<List<Object>> list = excelRead.getExcelContent(filePath);
		
		for(List<Object> temp : list){
			LoginBean loginBean = new LoginBean(); 
			loginBean.setUserName(temp.get(0).toString());
			loginBean.setPassword(temp.get(1).toString());
			loginList.add(loginBean); 
		}

		return loginList; 
	}
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		excelRead = new ApachePOIExcelRead(); 
		
	}

	@Before
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL"); 
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Test
	public void loginPassTest() {
			loginPOM.sendUserName(this.loginBean.getUserName());
			loginPOM.sendPassword(this.loginBean.getPassword());
			loginPOM.clickLoginBtn(); 
			screenShot.captureScreenShot(); 
	}
}
