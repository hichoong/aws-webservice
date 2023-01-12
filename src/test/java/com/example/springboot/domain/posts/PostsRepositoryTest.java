package com.example.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() throws Exception {
        //given
        String title = "게시글 제목";
        String content = "게시글 내용";
        //when
        postRepository.save(Posts.builder().title(title).content(content).author("user@gmial.com").build());
        List<Posts> postsList = postRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_확인() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 1, 12,0,0,0);
        postRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("저자")
                .build());
        //when
        List<Posts> postsList = postRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        System.out.println("createDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}