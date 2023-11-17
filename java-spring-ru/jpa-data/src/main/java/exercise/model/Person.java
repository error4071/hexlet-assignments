package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Cleanup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table()
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long Id;

    @Column(unique = true)
    private String firstName;

    private String lastName;
}
