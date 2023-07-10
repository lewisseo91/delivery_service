package com.delivery.post.dao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDao {

    private Long postId;

    private Long userId;

    private Long postStoreId;

    private Long commentId;

    private Boolean deleted;

    private LocalDateTime createdAt;

    public PostDao() {
    }

    public PostDao(Long postId, Long userId, Long postStoreId, Long commentId, Boolean deleted, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.postStoreId = postStoreId;
        this.commentId = commentId;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }
}
