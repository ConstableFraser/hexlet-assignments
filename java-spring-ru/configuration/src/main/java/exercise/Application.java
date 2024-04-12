package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private final List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties userProperties;

    @GetMapping("/admins")
    public ResponseEntity<List<String>> admins() {
        var admins = userProperties.getAdmins();

        var names = users.stream()
                .filter(u -> (admins.contains(u.getEmail())))
                .map(User::getName)
                .sorted(Comparator.naturalOrder())
                .toList();

        return ResponseEntity.ok()
                .body(names);
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
