package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private final String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println(name + " is initialized!");
    }
    // END
}
