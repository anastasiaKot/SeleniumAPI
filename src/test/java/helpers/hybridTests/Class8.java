package helpers.hybridTests;


import helpers.FacebookTestUserAccount;
import helpers.FacebookTestUserStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by abarabash on 6/30/16.
 */
public class Class8 {

    public static String userEmail = "";
    public static String userPassw = "";


    @Test
    public void test001Login() throws Exception {

        // start the proxy
//        ProxyServer server = new ProxyServer(4444);
//        server.start();
//
//        // get the Selenium proxy object
//        Proxy proxy = server.seleniumProxy();
//
//        // configure it as a desired capability
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, proxy);

        getTheIdAndPassword();
        
        // start the browser up
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("https://www.instagram.com/");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/span/button")));

        driver.findElement(By.xpath(".//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/span/button")).click();


        driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(userEmail);

        driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(userPassw);

        driver.findElement(By.xpath(".//*[@id='loginbutton']")).click();

    }



    public void getTheIdAndPassword() throws IOException {

        Properties props = getFacebookConnectionProperties();

        FacebookTestUserStore facebookstore = new FacebookTestUserStore(props.getProperty("facebook.appId1"), props.getProperty("facebook.appSecret1"));

        facebookstore.deleteAllTestUsers();

        FacebookTestUserAccount account = facebookstore.createTestUser(true, "read_stream,publish_actions,user_photos");

        userEmail = account.getEmail();
        userPassw = account.getPassword();


    }

    private static Properties getFacebookConnectionProperties() throws IOException {

        Properties properties = new Properties();

        InputStream stream = FacebookTestUserStore.class.getClassLoader().getResourceAsStream("facebook-app.properties");

        properties.load(stream);

        stream.close();

        return properties;
    }


}
