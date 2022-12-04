package com.kumacode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// https://youtu.be/-mwpoE0x0JQ?t=3605
@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse("Good Morning",
                List.of("Java", "Javascript", "Golang"),
                new Person("Alex", 30, 40_000));

        return response;
    }

    record  Person(String name, int age, double cash){
    }
    record GreetResponse(String greet,
                         List<String> favProgrammingLanguages,
                         Person person){
    }
}
