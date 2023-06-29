package dev.danvega.serverlessapi.post;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("/posts")
    List<Post> loadPosts();

}
