package com.sparta.spartabulletinboardbackend.dto.post;

import com.sparta.spartabulletinboardbackend.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class PostReadAllResponse {

    private final Map<String, List<PostReadAllData>> posts = new HashMap<>();

    @Builder
    public PostReadAllResponse(List<Post> posts) {
        for (Post post : posts) {
            String username = post.getUser().getUsername();
            if(this.posts.get(username) == null) this.posts.put(username, new ArrayList<>());
            this.posts.get(username).add(new PostReadAllData(post));
        }
    }

    @Getter
    static class PostReadAllData {
        private final Long postId;
        private final String title;
        private final boolean success;
        private final String createdAt;

        public PostReadAllData(Post post) {
            this.postId = post.getId();
            this.title = post.getTitle();
            this.success = post.isSuccess();
            this.createdAt = post.getCreatedAt().toString();
        }
    }
}
