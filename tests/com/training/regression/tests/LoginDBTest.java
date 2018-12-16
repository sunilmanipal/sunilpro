package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.training.dao.ELearningDAO;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/**
 * 
 * @author Naveen
 * @see program to show working of parameterization with DB 
 */

@RunWith(Parameterized.class)
public class LoginDBTest {
	private WebDriver driver; 
	private String baseUrl; 
	private LoginPOM loginPOM; 
	private static Properties properties; 
	private ScreenShot screenShot; 


	// parameterized variables 
	private String userName; 
	private String password; 
	
	public LoginDBTest(LoginBean loginBean) {
		this.userName = loginBean.getUserName(); 
		this.password = loginBean.getPassword(); 
	}
	

	// jUnit will invoke the static method which is annotated with Parameters 
	// and the array of value which are generated from this method 
	// will be injected to contractors 
	@Parameters
	public static List<LoginBean> data(){
		// return List of Array of Object 
		
		// the values here can be hard coded 
		// or pull from database or excel sheet 
		
		
		/*return Arrays.asList(new String[][]{
			{"Ashwini", "secret@111"}, 
			{"Krishna", "secret@2323"}, 
			{"Laxmi", "secret@11"}, 
			{"Michael", "333434@hello"}, 
			{"Ravi", "secret@13441"}, 
			{"Siby", "secret@5551"}, 
			{"Tripti", "secret@11555"}, 
			
		});*/
		
		
		
		return new ELearningDAO().getLogins(); 
		
		
		
		
		
	}
	
	
	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@Before
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL"); 
		screenShot = new ScreenShot(driver); 
//		eLearningDAO = new ELearningDAO(); 
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Test
	public void loginPassTest() {
			loginPOM.sendUserName(this.userName);
			loginPOM.sendPassword(this.password);
			loginPOM.clickLoginBtn(); 
			screenShot.captureScreenShot(); 
	}
	
	
}
