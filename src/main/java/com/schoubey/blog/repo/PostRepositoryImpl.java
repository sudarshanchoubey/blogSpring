package com.schoubey.blog.repo;

import com.schoubey.blog.model.PostLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.LinkedList;

@Component
public class PostRepositoryImpl implements PostRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public LinkedList<PostLine> getAllPosts() {
    LinkedList<PostLine> postLines = new LinkedList<>();
    jdbcTemplate.query(
        "SELECT id, title, createTime, updateTime FROM posts",
        (rs, rowNum) -> new PostLine(
            rs.getString("id"),
            rs.getString("title"),
            ZonedDateTime.parse(rs.getString("createTime"), PostLine.postDTF),
            ZonedDateTime.parse(rs.getString("updateTime"), PostLine.postDTF)
        )
    ).forEach(post -> {
      postLines.add(post);
      System.out.println(post.toString());
    });
    return postLines;
  }
}
