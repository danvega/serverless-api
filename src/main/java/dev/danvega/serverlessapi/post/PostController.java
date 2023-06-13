package dev.danvega.serverlessapi.post;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();

    @GetMapping
    List<Post> findAll() {
        return posts;
    }

    @PostConstruct
    private void init() {
        posts = List.of(new Post(1,"Hello, World!","This is my first blog post."));
    }

}
