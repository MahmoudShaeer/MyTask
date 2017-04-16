package com.example.mahmoudshaeer.mytask;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

public class PostInformation {
    String id;
    String title;
    String body;

    public PostInformation() {
    }

    public PostInformation(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
