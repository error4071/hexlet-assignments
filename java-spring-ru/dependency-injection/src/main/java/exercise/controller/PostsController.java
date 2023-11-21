package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.model.Comment;
import exercise.repository.CommentRepository;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post show(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping(path = "/{id}")
    public Post update(@PathVariable long id, @RequestBody Post postData) {
        Post maybePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        maybePost.setTitle(postData.getTitle());
        maybePost.setBody(postData.getBody());

        postRepository.save(maybePost);

        return maybePost;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
