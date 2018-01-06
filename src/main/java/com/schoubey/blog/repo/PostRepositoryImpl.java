package com.schoubey.blog.repo;

import com.schoubey.blog.model.*;
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

    public PostContent getPostContent(String id) {
      String title = jdbcTemplate.query(
          "SELECT title FROM posts where id = ?", new Object[]{id},
          (rs) ->
          {
            rs.first();
            return rs.getString("title");
          });
      String content = jdbcTemplate.query(
          "SELECT content FROM post_content where id = ?", new Object[]{id},
          (rs) ->
          {
            rs.first();
            return rs.getString("content");
          });
      return new PostContent(id, content, title);
    }

    @Override
    public void savePost(Post post) {
        jdbcTemplate.update("INSERT into posts(id, title, createTime, updateTime) values(?, ?, ?, ?)",
          post.getId(),
          post.getTitle(),
          post.getCreateTime().format(PostLine.postDTF),
          post.getUpdateTime().format(PostLine.postDTF));

        jdbcTemplate.update("INSERT into post_content(id, content) values(?, ?)",
          post.getId(),
          post.getContent());
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update("UPDATE posts SET title = ?, updateTime = ? WHERE id = ?",
          new Object[]{
            post.getTitle(),
            post.getUpdateTime().format(PostLine.postDTF),
            post.getId()
        });
        jdbcTemplate.update("UPDATE post_content SET content=? WHERE id = ?",
          new Object[]{
            post.getContent(),
            post.getId()
        });
    }
}
