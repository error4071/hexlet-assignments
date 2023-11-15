package exercise.controller.users;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api/users")
public class PostsController {
    List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable int id) {
        return posts.stream()
                .filter(post -> post.getUserId() == id)
                .collect(Collectors.toList());
        }

    @PostMapping("/{id}/posts/")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post, @PathVariable int id) {
        Post newPost = new Post();

        newPost.setUserId(id);
        newPost.setSlug(post.getSlug());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        posts.add(newPost);

        return newPost;
    }
}
