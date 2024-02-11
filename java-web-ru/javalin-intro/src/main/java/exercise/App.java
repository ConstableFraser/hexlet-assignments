package exercise;

// BEGIN
import io.javalin.Javalin;
// END

public final class App {
    public static Javalin getApp() {
        // BEGIN
        var app = Javalin.create(javalinConfig -> javalinConfig.bundledPlugins.enableDevLogging());
        app.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
        return app;
        // END
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
