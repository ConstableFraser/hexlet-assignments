package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index() {
        var posts = postRepository.findAll().stream()
                .map(Post::toDTO)
                .toList();

        for (PostDTO post : posts) {
            var comments = commentRepository.findByPostId(post.getId()).stream()
                    .map(Comment::toDTO)
                    .toList();
            post.setComments(comments);
        }
        return posts;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var postDTO = post.toDTO();
        postDTO.setComments(commentRepository.findByPostId(post.getId()).stream()
                .map(Comment::toDTO)
                .toList());
        return postDTO;
    }
}
// END
