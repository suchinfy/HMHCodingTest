package org.favqs.tests;

import io.restassured.response.Response;
import org.favqs.POJO.Quote;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.*;

public class FavQuotesTest extends BaseTest {
    int favQuoteId=29932;
    int invalidQuoteId=99999;

    @Test
    public void TestMarkFavorite() {
        String favQuotesEndpoint = String.format(Routes.favQuote, favQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        Reporter.log(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        Quote responseQuote = response.body().as(Quote.class);
        assertThat(responseQuote.getId(), equalTo(favQuoteId));
        assertTrue(responseQuote.getUser_details().getFavorite());
    }

    @Test
    public void TestQuoteNotFound() {
        String favQuotesEndpoint = String.format(Routes.favQuote, invalidQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        Reporter.log(response.body().prettyPrint());
        response.then().statusCode(equalTo(404));
        response.then().body("status", equalTo(404));
        response.then().body("error", equalTo("Not Found"));
    }

    @Test
    public void TestUnmarkFavorite() {
        String favQuotesEndpoint = String.format(Routes.unFavQuote, favQuoteId);
        Response response = BaseSpecUtil.putRequest(authToken, userToken, Routes.baseUrl + favQuotesEndpoint);
        Reporter.log(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        Quote responseQuote = response.body().as(Quote.class);
        assertThat(responseQuote.getId(), equalTo(favQuoteId));
        assertFalse(responseQuote.getUser_details().getFavorite());
    }
}
