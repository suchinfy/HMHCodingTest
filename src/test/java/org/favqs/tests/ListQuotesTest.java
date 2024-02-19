package org.favqs.tests;

import io.restassured.response.Response;
import org.favqs.POJO.ListQuotes;
import org.favqs.Routes;
import org.favqs.utils.BaseSpecUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

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

        assertEquals(response.getStatusCode(), 200);
        ListQuotes results = response.body().as(ListQuotes.class);
        assertThat(results.getQuotes().size(), greaterThan(0));
    }

    @Test
    public void testListQuotesWithFilter() {
        Response response = BaseSpecUtil.getRequest(authToken, userToken, Routes.baseUrl + Routes.listQuotes + "?filter=funny&type=tag");
        Reporter.log(response.body().prettyPrint());

        assertEquals(response.getStatusCode(), 200);
        ListQuotes results = response.body().as(ListQuotes.class);

        // assert each quote in the result has at least one of the tags as 'funny'
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

        // assert the quote id which was hidden is present in one of the results
        assertEquals(1,
                results.getQuotes().stream().filter(q -> q.getId().equals(hideQuoteId)).count()
        );

        // assert each quote in the result has hidden attribute as true
        results.getQuotes().forEach(
                q -> assertTrue(q.getUser_details().getHidden())
        );
    }
}
