package com.delivery.post.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * JPA 의존성
 * 현재는 테이블 생성 용도로만 쓰고 있음
 */
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long postId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="post_store_id")
    private Long postStoreId;

    @Column(name="comment_id")
    private Long commentId;

    @Column(name="deleted")
    private Boolean deleted;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(Long postId, Long userId, Long postStoreId, Long commentId, Boolean deleted, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.postStoreId = postStoreId;
        this.commentId = commentId;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }
}
