package org.favqs.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private Integer id;
    private Boolean dialogue;
    private String source;
    private String context;
    @JsonProperty("private")
    private Boolean isPrivate;
    private List<String> tags;
    private String url;
    private int favorites_count;
    private int upvotes_count;
    private int downvotes_count;
    private String author;
    private String author_permalink;
    private String body;
    private List<Line> lines;

    private User_details user_details;

    public Integer getId() {
        return id;
    }

    public Boolean getDialogue() {
        return dialogue;
    }

    public String getSource() {
        return source;
    }

    public String getContext() {
        return context;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public int getFavorites_count() {
        return favorites_count;
    }

    public int getUpvotes_count() {
        return upvotes_count;
    }

    public int getDownvotes_count() {
        return downvotes_count;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthor_permalink() {
        return author_permalink;
    }

    public String getBody() {
        return body;
    }

    public List<Line> getLines() {
        return lines;
    }

    public User_details getUser_details() {
        return user_details;
    }
}
