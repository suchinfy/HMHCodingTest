package org.favqs.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.favqs.POJO.ListQuotes;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ListQuotesTest extends BaseTest {
    int hideQuoteId = 6843;

    @Test
    public void testListQuotes() {
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes);
        Reporter.log(response.body().prettyPrint());
        response.then().statusCode(equalTo(200));
        ListQuotes results = response.body().as(ListQuotes.class);
        assertThat(results.getQuotes().size(), greaterThan(0));
    }

    @Test
    public void testListQuotesWithFilter() {
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes + "?filter=funny&type=tag");
        Reporter.log(response.body().prettyPrint());
        response.then().statusCode(equalTo(200));
        ListQuotes results = response.body().as(ListQuotes.class);
        results.getQuotes().forEach(
                result -> assertThat(result.getTags(), hasItem("funny"))
        );
    }

    @Test
    public void testListHiddenQuotes() {
        hideQuote(authToken, hideQuoteId, userToken);
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes + "?hidden=1");
        Reporter.log(response.body().prettyPrint());
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
