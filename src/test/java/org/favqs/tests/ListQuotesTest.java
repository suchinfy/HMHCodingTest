package org.favqs.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.favqs.POJO.ListQuotes;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ListQuotesTest extends BaseTest {
    String login;
    String password;
    String authToken;
    String userToken;
    int hideQuoteId = 6843;

    @BeforeClass
    public void createUserAndAuthorize() {
        login = System.getProperty("login");
        password = System.getProperty("password");
        authToken = System.getProperty("authToken");
        userToken = getUserSession(login, password, authToken);
    }

    @Test
    public void testListQuotes() throws JsonProcessingException {
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes);
        System.out.println(response.getBody().asString());
        response.then().statusCode(equalTo(200));
        ListQuotes results = response.body().as(ListQuotes.class);
        assertThat(results.getQuotes().size(), greaterThan(0));
    }

    @Test
    public void testListQuotesWithFilter() throws JsonProcessingException {
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes + "?filter=funny&type=tag");
        System.out.println(response.getBody().asString());
        response.then().statusCode(equalTo(200));
        ListQuotes results = response.body().as(ListQuotes.class);
        results.getQuotes().forEach(
                result -> assertThat(result.getTags(), hasItem("funny"))
        );
    }

    @Test
    public void testListHiddenQuotes() throws JsonProcessingException {
        hideQuote(authToken, hideQuoteId, userToken);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("hidden", "1");
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes + "?hidden=1");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        ListQuotes results = response.body().as(ListQuotes.class);
        assertEquals(1,
                results.getQuotes().stream().filter(q -> q.getId().equals(hideQuoteId)).count()
        );
        results.getQuotes().forEach(
                q -> assertTrue(q.getUser_details().getHidden())
        );
    }

}
