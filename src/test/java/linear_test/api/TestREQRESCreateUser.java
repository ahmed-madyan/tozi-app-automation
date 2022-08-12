package linear_test.api;

import api.pojo_request_payload.reqress.CreateUser;
import api.pojo_response_payload.create_user.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.api_driver.RequestBuilder;
import utilities.api_driver.RequestMethod;
import utilities.reader_manager.properties_reader.ConfigUtils;

public class TestREQRESCreateUser {
    private static final CreateUser createUser = new CreateUser();
    private static User user = new User();
    private static Response response;

    @BeforeClass
    public Response sendRequest() {
        createUser.setName("Ahmed Madyan");
        createUser.setJob("Senior Test Automation Engineer");
        response = RequestBuilder.performRequest(ConfigUtils.getRequires_BaseURI(), null, ConfigUtils.getRequires_BasePath(), createUser, RequestMethod.POST, null, null, null, HttpStatus.SC_CREATED);
        user = response.as(User.class);
        System.out.println(response.getStatusCode());
        return response;
    }

    @Test
    public static void assertResponseCode() {
        response.then().statusCode(HttpStatus.SC_CREATED);
        Assert.assertFalse(user.getCreatedAt().isEmpty());
    }
}