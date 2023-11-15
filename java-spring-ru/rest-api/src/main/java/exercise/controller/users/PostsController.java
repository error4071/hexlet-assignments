package exercise.controller.users;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users")
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
        }
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post newPost = new Post();

        newPost.setSlug(post.getSlug());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        posts.add(newPost);

        URI location = URI.create("/posts");

        return ResponseEntity.created(location).body(newPost);
    }
    }
