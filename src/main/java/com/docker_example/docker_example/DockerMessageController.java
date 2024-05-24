package com.docker_example.docker_example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class DockerMessageController {
    @GetMapping("/messages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}
