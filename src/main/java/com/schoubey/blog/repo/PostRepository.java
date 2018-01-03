package com.schoubey.blog.repo;

import com.schoubey.blog.model.PostLine;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface PostRepository {
  public LinkedList<PostLine> getAllPosts();
}
