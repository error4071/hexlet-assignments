package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private final List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index() {
        var result = posts;

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return ResponseEntity.of(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post newPost = new Post();

        newPost.setId(post.getId());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        posts.add(newPost);

        URI location = URI.create("/posts");

        return ResponseEntity.created(location).body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        var postFromBase = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (postFromBase.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(post);
        }

        postFromBase.get().setTitle(post.getTitle());
        postFromBase.get().setBody(post.getBody());

        posts.add(postFromBase.get());

        return ResponseEntity.ok().body(postFromBase.get());
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}