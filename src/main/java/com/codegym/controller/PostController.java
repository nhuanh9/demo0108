package com.codegym.controller;

import com.codegym.model.Post;
import com.codegym.model.User;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    IPostService postService;

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> findAll() {
        Iterable<Post> posts = postService.findAll();
        return new ResponseEntity(posts, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Post> save(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
