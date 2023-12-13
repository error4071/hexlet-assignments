package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String clubCard;
    private LocalDate clubValidUntil;
}
// END
