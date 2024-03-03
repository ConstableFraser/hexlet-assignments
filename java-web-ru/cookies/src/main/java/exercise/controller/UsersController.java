package exercise.controller;

import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import java.util.Objects;

import exercise.repository.UserRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;


public class UsersController {

    public static void build(Context ctx) {
        ctx.render("users/build.jte").status(200);
    }

    // BEGIN
    public static void register(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = String.valueOf(ctx.formParam("email")).trim().toLowerCase();
        var password = ctx.formParam("password");
        var encryptedPassword = Security.encrypt(password == null ? "" : password);
        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, encryptedPassword, token);
        var id = UserRepository.save(user);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
        var tokenUser = String.valueOf(user.getToken());

        if (Objects.equals(tokenUser, ctx.cookie("token"))) {
            ctx.render("users/show.jte", Collections.singletonMap("user", user));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
