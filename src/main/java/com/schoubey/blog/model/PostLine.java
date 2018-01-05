package com.schoubey.blog.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class PostLine {
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateTime() {
        return createTime.toString();
    }

    public String getUpdateTime() {
        return updateTime.toString();
    }

    private String id;
    private String title;
    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;

    public PostLine(String id, String title, ZonedDateTime createTime, ZonedDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String toString() {
        return String.format(
            "Post[id=%s, title='%s', createTime='%s'], updateTime=%s",
            id, title, createTime.toString(), updateTime.toString()
        );
    }



    public static DateTimeFormatter postDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nxxx");
}
