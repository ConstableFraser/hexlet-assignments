package exercise.dto;

import lombok.Getter;
import lombok.Setter;

// import java.time.LocalDate;

@Setter
@Getter
public class ProductUpdateDTO {
    private String title;
    private int price;
//    private LocalDate updatedAt;
}
