package exercise.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercise.model.Person;

@Repository
@SpringBootApplication
public interface PersonRepository extends JpaRepository<Person, Long> {
    }
