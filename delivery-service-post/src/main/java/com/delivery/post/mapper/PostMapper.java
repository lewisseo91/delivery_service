package com.delivery.post.mapper;

import com.delivery.post.dao.PostDao;
import com.delivery.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<PostDao> findAll();

    void addPost(Post post);
}
