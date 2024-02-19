package org.favqs.tests;

import io.restassured.response.Response;
import org.favqs.POJO.Quote;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.*;

public class FavQuotesTest extends BaseTest {
    String login;
    String password;
    String authToken;
    String userToken;
    int favQuoteId=29932;
    int invalidQuoteId=99999;

    @BeforeClass
    public void createUserAndAuthorize() {
        login = System.getProperty("login");
        password = System.getProperty("password");
        authToken = System.getProperty("authToken");
        userToken = getUserSession(login, password, authToken);
    }

    @Test
    public void TestMarkFavorite() {
        String favQuotesEndpoint = String.format(Routes.favQuote, favQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Quote responseQuote = response.body().as(Quote.class);
        assertThat(responseQuote.getId(), equalTo(favQuoteId));
        assertTrue(responseQuote.getUser_details().getFavorite());
    }

    @Test
    public void TestQuoteNotFound() {
        String favQuotesEndpoint = String.format(Routes.favQuote, invalidQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        System.out.println(response.getBody().asString());
        response.then().statusCode(equalTo(404));
        response.then().body("status", equalTo(404));
        response.then().body("error", equalTo("Not Found"));
    }

    @Test
    public void TestUnmarkFavorite() {
        String favQuotesEndpoint = String.format(Routes.unFavQuote, favQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Quote responseQuote = response.body().as(Quote.class);
        assertThat(responseQuote.getId(), equalTo(favQuoteId));
        assertFalse(responseQuote.getUser_details().getFavorite());
    }
}
