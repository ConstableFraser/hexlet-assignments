package exercise.controller;

import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import java.util.Objects;

import exercise.repository.UserRepository;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) {
        ctx.render("users/build.jte");
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
        UserRepository.save(user);

        var id = UserRepository.search(firstName);
        ctx.redirect(NamedRoutes.userPath(String.valueOf(id)));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class);
        var user = UserRepository.find(id.get()).get();
        var tokenUser = String.valueOf(user.getToken());

        if (Objects.equals(tokenUser, ctx.cookie("token"))) {
            ctx.render("users/show.jte", Collections.singletonMap("page", user));
        }
        ctx.redirect(NamedRoutes.buildUserPath());
    }
    // END
}
