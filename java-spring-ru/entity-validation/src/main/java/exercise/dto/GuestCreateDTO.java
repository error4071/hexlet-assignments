package exercise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    @Size(min = 11, max = 13)
    private String phoneNumber;

    @NotNull
    @Size(min = 4, max = 4)
    private String clubCard;

    @Valid
    private LocalDate cardValidUntil;
}
// END
