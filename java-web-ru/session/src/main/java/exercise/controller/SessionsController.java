package exercise.controller;

import java.util.Collections;
import java.util.Map;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import lombok.extern.java.Log;

public class SessionsController {
    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void signin(Context ctx) {
        var name = ctx.formParam("name");
        var password = encrypt(ctx.formParam("password"));

        var page = new LoginPage(name, "Wrong username or password");

        UsersRepository.getEntities().stream()
                .filter((user) -> (user.getName().equals(name) && user.getPassword().equals(password)))
                .findFirst()
                .ifPresentOrElse((u) -> {
                            ctx.sessionAttribute("currentUser", u.getName());
                            ctx.redirect(NamedRoutes.rootPath());
                        },
                        () -> {
                            ctx.render("build.jte", Map.of("page", page));
                        });
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void index(Context ctx) {
        var name = ctx.sessionAttribute("currentUser");
        var page = new MainPage(name);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
    // END
}
