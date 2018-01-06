package com.schoubey.blog.controllers;

import com.schoubey.blog.model.Post;
import com.schoubey.blog.model.PostContent;
import com.schoubey.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("posts", postRepository.getAllPosts());
        return "index";
    }

    @GetMapping("/posts/{id}")
    String showPost(@PathVariable String id, Model model) {
        model.addAttribute("postContent", PostContent.processContent(postRepository.getPostContent(id)));
        return "post";
    }

    @GetMapping("/post")
    String showForm(@RequestParam(value="id", required = false, defaultValue = "") String id, Model model) {
        if (id.equals("")) {
            model.addAttribute("post", null);
            return "editPost";
        } else {
            PostContent postContent = postRepository.getPostContent(id);
            model.addAttribute("post", postContent);
            return "editPost";
        }
    }

    @PostMapping(path = "/posts", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String savePost(@RequestParam Map<String, String> paramMap) {
        if (paramMap == null) {
            return "redirect:post";
        }
        if (paramMap.get("id").equals("")) {
            String id = java.util.UUID.randomUUID().toString();
            ZonedDateTime now = ZonedDateTime.now();
            Post post = new Post(
              id,
              paramMap.get("title").toString(),
              paramMap.get("content").toString(),
              now, now
            );
            postRepository.savePost(post);
        } else {
            String id = paramMap.get("id");
            ZonedDateTime now = ZonedDateTime.now();
            Post post = new Post(
              id,
              paramMap.get("title").toString(),
              paramMap.get("content").toString(),
              now, now
            );
            postRepository.updatePost(post);
            return "redirect:/posts/" + id;
        }
        return "redirect:";
    }

    @RequestMapping(value = "/time")
    @ResponseBody
    String time() {
      return java.time.ZonedDateTime.now().toString();
    }

}

