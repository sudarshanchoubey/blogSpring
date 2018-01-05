package com.schoubey.blog.repo;

import com.schoubey.blog.model.Post;
import com.schoubey.blog.model.PostContent;
import com.schoubey.blog.model.PostLine;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface PostRepository {
    public LinkedList<PostLine> getAllPosts();
    public PostContent getPostContent(String id);
    public void savePost(Post post);
}
