package exercise;

import io.javalin.Javalin;

import java.util.Comparator;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
           var term = ctx.queryParam("term");
           var users = USERS.stream()
                   .filter(u -> u.getFirstName().startsWith(term == null ? "" : term))
                   .sorted(Comparator.comparingInt(u -> (int) u.getId()))
                   .toList();
           var page = new UsersPage(users.isEmpty() ? List.of() : users, term);
           ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
