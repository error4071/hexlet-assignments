package exercise.controller;

import exercise.repository.PersonRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    @GetMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public Person index(@PathVariable Long id) {
        var person = personRepository.findById(id).get();
        return person;
    }
    @PostMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public Person create(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @DeleteMapping("/people/{id}")
    public void destroy(@PathVariable String id) {
    personRepository.deleteAll();
    }
}
