package com.delivery.post.service;

import com.delivery.post.dao.PostDao;
import com.delivery.post.dto.PostRequest;
import com.delivery.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public List<PostDao> findAllPosts() {
        return postMapper.findAll();
    }

    @Transactional
    public void addPost(PostRequest postRequest) {
        postMapper.addPost(postRequest.to());
    }
}
