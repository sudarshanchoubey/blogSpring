package com.schoubey.blog.model;

import java.time.ZonedDateTime;

public class Post {

    private String id;
    private String title;
    private String content;
    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;

    public Post(String id, String title, String content, ZonedDateTime createTime, ZonedDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getTitle() { return title; }

    public String getId() {
      return id;
    }

    public String getContent() {
      return content;
    }

    public ZonedDateTime getCreateTime() {
      return createTime;
    }

    public ZonedDateTime getUpdateTime() {
      return updateTime;
    }


}
