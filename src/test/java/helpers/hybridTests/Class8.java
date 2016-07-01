package helpers.hybridTests;

import helpers.FacebookTestUserAccount;
import helpers.FacebookTestUserStore;
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
    private FacebookTestUserAccount account;
    private FacebookTestUserStore facebookstore;


   @Test
   public void loginTest(){



   }


    public void getTheIdAndPassword() throws IOException {

        Properties props = getFacebookConnectionProperties();

        facebookstore = new FacebookTestUserStore(props.getProperty("facebook.appId1"), props.getProperty("facebook.appSecret1"));

        facebookstore.deleteAllTestUsers();

        account = facebookstore.createTestUser(true, "read_stream,publish_actions,user_photos,user_about_me,email,user_status,read_custom_friendlists,user_friends,user_location,user_hometown");

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
