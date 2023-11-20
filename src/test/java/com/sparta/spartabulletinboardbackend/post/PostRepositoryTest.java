package com.sparta.spartabulletinboardbackend.post;

import com.sparta.spartabulletinboardbackend.domain.Post;
import com.sparta.spartabulletinboardbackend.domain.user.User;
import com.sparta.spartabulletinboardbackend.domain.user.UserRole;
import com.sparta.spartabulletinboardbackend.repository.PostRepository;
import com.sparta.spartabulletinboardbackend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired private EntityManager em;
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;
    @Test
    @Transactional
    @Rollback(value = false)
    public void findAllGroupByUserOrderByCreatedAtDesc() {
        //given
        User user1 = new User(UserRole.USER, "zzzzseong", "zzzzseong");
        userRepository.save(user1);

        postRepository.save(new Post(user1, "z_title1", "z_content1"));
        postRepository.save(new Post(user1, "z_title2", "z_content2"));

        User user2 = new User(UserRole.USER, "jisung", "jisung");
        userRepository.save(user2);

        postRepository.save(new Post(user2, "j_title1", "j_content1"));
        postRepository.save(new Post(user2, "j_title2", "j_content2"));

//        List<Post> all = postRepository.findAllPostWithUsername();
//        for (Post post : all) {
//            User user = post.getUser();
//            System.out.print("user.getUsername() = " + user.getUsername() + " ");
//            System.out.println("post.getTitle() = " + post.getTitle());
//        }
    }
}
