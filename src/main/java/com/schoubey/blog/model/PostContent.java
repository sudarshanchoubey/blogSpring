package com.schoubey.blog.model;

public class PostContent {
  private String id;
  private String content;
  private String title;

  public PostContent(String id, String content, String title) {
    this.id = id;
    this.content = content;
    this.title = title;
  }

  public String getContent() { return content; }

  public String getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }
}
