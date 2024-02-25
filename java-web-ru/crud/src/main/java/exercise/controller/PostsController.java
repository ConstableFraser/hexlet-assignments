package exercise.controller;

import java.util.Collections;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {
    // BEGIN
    public static void show(Context ctx) {
        var postId = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(postId)
                        .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    public static void index(Context ctx) {
        var pageNum = Math.max(1, ctx.queryParamAsClass("page", Integer.class).getOrDefault(1));
        var maxNum = pageNum * 5;
        if (pageNum * 5 > PostRepository.getEntities().size()) {
            maxNum = PostRepository.getEntities().size() - 1;
            pageNum = PostRepository.getEntities().size() / 5 + 1;
        }
        var posts = PostRepository.getEntities().subList((pageNum - 1) * 5, maxNum);
        var page = new PostsPage(posts, pageNum);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }
    // END
}
