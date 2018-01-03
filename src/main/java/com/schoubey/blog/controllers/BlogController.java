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

    @RequestMapping("/")
    String home(@RequestParam(value="name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("posts", postRepository.getAllPosts());
        return "index";
    }

    @RequestMapping("/time")
    @ResponseBody
    String time() {
      return java.time.ZonedDateTime.now().toString();
    }

}

