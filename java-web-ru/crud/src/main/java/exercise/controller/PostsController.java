package exercise.controller;

import java.util.*;
import java.util.stream.Collectors;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var term = ctx.queryParam("page") == null ? "1" : ctx.queryParam("page");
        var allPosts = PostRepository.getEntities();
        int num = Integer.parseInt(term);

        var posts = allPosts
                .stream()
                .sorted(Comparator.comparingLong(Post::getId))
                .skip(num * 5L)
                .limit(5)
                .collect(Collectors.toList());

        var page = new PostsPage(posts, term);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id);

        if (Objects.equals(null, post)) {
            throw new NotFoundResponse("Page with id = " + id + " not found");
        }

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}