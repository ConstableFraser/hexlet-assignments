package exercise;

import java.util.List;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private final List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts") // INDEX
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        var list =  posts.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(list);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(post);
    }

    @PostMapping("/posts") // CREATE
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/posts/{id}") // UPDATE
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setId(data.getId());
            page.setTitle(data.getTitle());
            page.setBody(data.getBody());
            return ResponseEntity.ok()
                    .body(page);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(data);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
