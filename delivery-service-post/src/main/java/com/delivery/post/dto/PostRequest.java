package com.delivery.post.dto;

import com.delivery.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long postId;

    private Long userId;

    private Long postStoreId;

    private Long commentId;

    private Boolean deleted;

    private LocalDateTime createdAt;

    public Post to() {
        return Post.builder()
                .postId(postId)
                .userId(userId)
                .postStoreId(postStoreId)
                .commentId(commentId)
                .deleted(deleted)
                .createdAt(createdAt)
                .build();
    }
}
