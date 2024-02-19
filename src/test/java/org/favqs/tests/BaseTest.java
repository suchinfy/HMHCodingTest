package org.favqs.tests;

import io.restassured.response.Response;
import org.favqs.utils.RequestBodyBuilder;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    String login;
    String password;
    static String authToken;
    static String userToken;
    @BeforeSuite
    public void createUserAndAuthorize() {
        login = System.getProperty("login");
        password = System.getProperty("password");
        authToken = System.getProperty("authToken");
        userToken = getUserSession(login, password, authToken);
    }
    public String getUserSession(String login, String password, String authToken) {
        String requestBody = RequestBodyBuilder.buildCreateSessionRequestBody(login, password);
        Response response = BaseSpecUtil.postRequest(authToken, requestBody, Routes.baseUrl + Routes.createSession);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        return response.jsonPath().get("User-Token");
    }

        public void hideQuote(String authToken, int quoteId, String userToken) {
            String hideQuotesEndpoint = String.format(Routes.hideQuote, quoteId);
            Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + hideQuotesEndpoint);
            System.out.println(response.getBody().asString());
            Assert.assertEquals(response.getStatusCode(), 200);
        }
}
