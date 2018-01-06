package com.schoubey.blog.model;

import com.github.rjeschke.txtmark.Processor;

public class PostContent {
    private String id;
    private String content;
    private String title;

    public void setContent(String content) {
    this.content = content;
  }

    public void setTitle(String title) {
    this.title = title;
  }

    public PostContent(String id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() { return content; }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public static PostContent processContent(PostContent postContent) {
        return new PostContent(postContent.id, Processor.process(postContent.content), postContent.title);
    }
}
