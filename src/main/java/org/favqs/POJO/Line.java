package org.favqs.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {
    private int position;
    private String body;
    private String author;
    private String author_permalink;

}
