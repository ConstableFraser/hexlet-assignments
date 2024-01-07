package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car unserialize(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(jsonRepresentation).toAbsolutePath().normalize();
        try {
            return mapper.readValue(path.toFile(), Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
