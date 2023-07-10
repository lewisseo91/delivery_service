package com.delivery.post.controller;

import com.delivery.post.dao.PostDao;
import com.delivery.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostDao>> findAllPosts() {
        return ResponseEntity.ok()
                .body(postService.findAllPosts());
    }
}
