package exercise;

// BEGIN
import io.javalin.Javalin;
// END

public final class App {
    public static Javalin getApp() {
        // BEGIN
        return Javalin.create(config -> config.plugins.enableDevLogging());
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
        app.get("/", ctx -> ctx.result("Hello World!"));
        app.start(7070);
    }
}
