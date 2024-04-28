package exercise.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestCreateDTO {
    // BEGIN
    @NotBlank
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @Pattern(regexp = "^\\+\\d{11,13}", message = "<phoneNumber>")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}", message = "<clubCard>")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
    // END
}
