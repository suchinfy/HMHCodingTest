package org.favqs.POJO;

import java.util.List;

public class ListQuotes {
    private int page;
    private Boolean last_page;

    private List<Quote> quotes;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Boolean getLast_page() {
        return last_page;
    }

    public void setLast_page(Boolean last_page) {
        this.last_page = last_page;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
