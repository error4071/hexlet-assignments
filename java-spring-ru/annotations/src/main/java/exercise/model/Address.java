package exercise.model;

import exercise.annotation.Inspect;
public record Address(@Inspect String city, @Inspect int postalCode) {

    public String getFullAddress() {
        return city + " " + postalCode;
    }
}