package exercise.controller;

import exercise.repository.PersonRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import exercise.model.Person;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> index() {
        return personRepository.findAll();
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Map<String, String> newPerson) {
        Person person = new Person();
        person.setFirstName(newPerson.get("firstName"));
        person.setLastName(newPerson.get("lastName"));
        return personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
    personRepository.deleteById(id);
    }
}
