package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment show(@PathVariable long id) {
        return commentRepository.findById(id).get();
    }

    @PostMapping(path = "")
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping(path = "{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment comData) {
        var maybeComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id " + id + " not found"));

        maybeComment.setBody(comData.getBody());

        commentRepository.save(maybeComment);

        return maybeComment;
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable long id) {
        commentRepository.deleteByPostId(id);
    }

}
