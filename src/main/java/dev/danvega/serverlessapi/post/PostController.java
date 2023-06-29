package dev.danvega.serverlessapi.post;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final JsonPlaceholderService jsonPlaceholderService;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private List<Post> posts = new ArrayList<>();

    public PostController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping
    List<Post> findAll() {
        return posts;
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable Integer id) {
        return Optional.ofNullable(posts
                .stream()
                .filter(post -> post.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("Post with id: " + id + " not found.")));
    }

    @PostMapping
    void create(@RequestBody Post post) {
        posts.add(post);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Post post, @PathVariable Integer id) {
        posts.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .ifPresent(value -> posts.set(posts.indexOf(value),post));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        posts.removeIf(post -> post.id().equals(id));
    }

    @PostConstruct
    private void init() {
        if(posts.isEmpty()) {
            log.info("Loading Posts using JsonPlaceHolderService");
            posts = jsonPlaceholderService.loadPosts();
        }
    }

}
