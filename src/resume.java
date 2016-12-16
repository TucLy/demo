import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class resume {
private WebDriver driver;

@BeforeMethod
public void openHomePage(){
	System.setProperty("webdriver.firefox.marionette", "C:\\Selenium\\Batch1116\\SeleniumPractice\\driver\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.get("https://tucly123.herokuapp.com");
	System.out.println("Open Browser");
	String homeExpect="You need to sign in or sign up before continuing.";
	String homeActuall=driver.findElement(By.xpath("html/body/h3[2]")).getText();
	assertEquals(homeExpect,homeActuall);
	System.out.println("verify home title");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test(dataProvider = "testdata")
public void createAcc(String id, String address, String phone,String email, String pass){
	driver.findElement(By.xpath("html/body/div/a[1]")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	System.out.println("go to sign_up page");	
	driver.findElement(By.xpath(".//*[@id='user_name']")).sendKeys(id);
	System.out.println("input ID");
	driver.findElement(By.xpath(".//*[@id='user_address']")).sendKeys(address);
	System.out.println("input address");
	driver.findElement(By.xpath(".//*[@id='user_phone']")).sendKeys(phone);
	System.out.println("input phone number");
	driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(email);
	System.out.println("input email");
	driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(pass);
	System.out.println("input password");
	driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys(pass);
	System.out.println("inut password confirmation");
	driver.findElement(By.xpath(".//*[@id='new_user']/div[7]/input")).click();
	System.out.println("create account");
	driver.findElement(By.xpath(".//*[@id='mainNavBar']/ul/li[1]/a")).click();
	System.out.println("log out");
}

//the code below using to delete accout 
//@Test(dataProvider="testdata")
//public void deleteAcc(String id, String address, String phone,String email, String pass){
//	driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(email);
//	System.out.println("input email");
//	driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(pass);
//	System.out.println("input pass");
//	driver.findElement(By.xpath(".//*[@id='new_user']/div[2]/input")).click();
//	System.out.println("login");
//	driver.findElement(By.xpath(".//*[@id='mainNavBar']/ul/li[3]/a")).click();
//	System.out.println("click edit profile");
//	driver.findElement(By.xpath("html/body/div/p[2]/a")).click();
//	System.out.println("click cancel account");
//}
@AfterMethod
public void teardown(){
	driver.quit();
}
@DataProvider(name="testdata")
	public Object[][] createData()
		{
			return new Object[][]
			{
				{"abc123","12345 testing Dr, CA 99999","8000000001","test1@gmail.com","abcd1234"},
				{"abc234","23456 testing Dr, CA 99999","8000000002","test2@gmail.com","abcd1234"},
				{"abc345","34567 testing Dr, CA 99999","8000000003","test3@gmail.com","abcd1234"},
			};
		}
}
//this DataProvider below I'm using as data driven using data from excel
//@DataProvider(name="testdata")
//public Object[][] createData() throws IOException
//{
//		Object[][] arrayObject= ReadExcel.getExcelData("excel path location","sheet#);
//		return arrayObject;
//}