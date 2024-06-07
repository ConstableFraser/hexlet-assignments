package exercise;

import java.io.IOException;
//import java.nio.file.OpenOption;
//import java.sql.SQLOutput;
//import java.nio.file.NoSuchFileException;
import java.util.concurrent.CompletableFuture;
//import java.util.Arrays;
//import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
// import java.io.File;
import java.nio.file.StandardOpenOption;
// import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1,
                                                       String filePath2,
                                                       String destPath) {

        if (!Files.exists(Path.of(filePath1)) || !Files.exists(Path.of(filePath2))) {
            System.out.println("NoSuchFileException");
        }

        CompletableFuture<String> result1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(filePath1));
            } catch (IOException e) {
                return e.getMessage();
            }
        });

        CompletableFuture<String> result2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(filePath2));
            } catch (IOException e) {
                return e.getMessage();
            }
        });

        return result1.thenCombine(result2, (r1, r2) -> {
            String content = r1 + r2;
            try {
                Files.writeString(Path.of(destPath), content, StandardOpenOption.WRITE);
            } catch (IOException e) {
                return e.getMessage();
            }
            return content;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return ex.getMessage();
        });
    }
    // END

    public static void main(String[] args) {
        // BEGIN
        var filePath1 = "src/main/resources/file1.txt";
        var filePath2 = "src/main/resources/file2.txt";
        var destPath = "src/main/resources/fileDestination.txt";

        unionFiles(filePath1, filePath2, destPath);
        // END
    }
}
