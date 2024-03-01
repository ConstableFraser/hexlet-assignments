package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN
    public static String patchPostPath(String id) {
        return "/posts/" + String.valueOf(id) + "/edit";
    }

    public static String patchPostPath(Long id) {
        return patchPostPath(String.valueOf(id));
    }
    // END
}
