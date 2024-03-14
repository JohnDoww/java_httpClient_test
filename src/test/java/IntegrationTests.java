import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import helpers.BaseMethods;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Links;
import utils.UserInfo;


import java.io.IOException;


import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class IntegrationTests {

    /**
     * There is a class with API test.
     */

    XmlMapper xmlMapper;
    BaseMethods baseMethods = new BaseMethods();
    UserInfo userInfo = new UserInfo();
    Links links = new Links();
    HttpGet get;
    CloseableHttpResponse response;

    String responseBody;

    String expectTimeFormat = userInfo.getTIME_FORMAT();
    String testUserId = userInfo.getTestId();
    String testUserLogIn = userInfo.getTestLogIn();
    String testUserPassword = userInfo.getTestPassword();
    String authorizationToken = baseMethods.generateAccessToken(testUserLogIn, testUserPassword);

    /**
     *The method is handle XML file which we get as a response on the requests.
     * Handled data will be added to the User Info class
     */
    public void handleUserXmlResponse(String responseBody) throws JsonProcessingException {

        xmlMapper = new XmlMapper();

        userInfo = xmlMapper.readValue(responseBody, UserInfo.class);
    }


    @Test
    @DisplayName("Validate thee response with user`s data ")
    public void getUserInfoById() throws IOException, ParseException {

        get = new HttpGet(links.GET_USER() + testUserId);
        get.setHeader("Authorization", baseMethods.generateAccessToken(testUserLogIn, testUserPassword));
        response = HttpClientBuilder.create().build().execute(get);

        // Parse the XML content and map it to the User object
        responseBody = EntityUtils.toString(response.getEntity());
        handleUserXmlResponse(responseBody);

        assertThat("Status code is 200", SC_OK, is(equalTo(response.getCode())));
        assertThat("Value Enabled is of type boolean", userInfo.isEnabled(), instanceOf(Boolean.class));
        assertThat("Created date is exist and has valid format", userInfo.getCreated(), matchesPattern(expectTimeFormat));
        assertThat("Product id value is not null and exist in the response", userInfo.getProduct().length(), greaterThan(3));
        assertThat("Parent id value is not null and exist in the response", userInfo.getParent().length(), greaterThan(3));
        assertThat("Value Subscribed is of type boolean", userInfo.isSubscribed(), instanceOf(Boolean.class));
    }

    @Test
    @DisplayName("Login by test user")
    public void loginByTestUser() throws IOException, ParseException {

        get = new HttpGet(links.GET_USER());

        get.setHeader("Authorization", authorizationToken);
        response = HttpClientBuilder.create().build().execute(get);

        responseBody = EntityUtils.toString(response.getEntity());
        handleUserXmlResponse(responseBody);

        assertThat("Status code is 200", SC_OK, is(equalTo(response.getCode())));
        assertThat("User id value is not null and exist in the response", userInfo.getId().length(), greaterThan(3));
    }
}
