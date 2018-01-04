package com.schoubey.blog.controllers;

import com.schoubey.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    String home(@RequestParam(value="name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("posts", postRepository.getAllPosts());
        return "index";
    }

    @GetMapping("/posts/{id}")
    String showPost(@PathVariable String id, Model model) {
        model.addAttribute("postContent", postRepository.getPostContent(id));
        return "post";
    }

    @RequestMapping("/time")
    @ResponseBody
    String time() {
      return java.time.ZonedDateTime.now().toString();
    }

}

