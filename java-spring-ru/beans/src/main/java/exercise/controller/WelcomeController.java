package exercise.controller;

import exercise.Application;
import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    Application getDaytime;

    @GetMapping(path = "")
    String message() {
        String dayTime = Application.getDaytime()
                .getName();

        return String.format("It is " + dayTime + " now. Welcome to Spring.");
    }
}