package com.LinkedIn.studentclient;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor // to handle construction injection
public class StudentClient {

    @Autowired
    private final WebClient webClient;

    public Student getStudent(Long id) {

        return webClient.get()
                .uri("/students/{id}", id)
                .retrieve()
                .bodyToMono(Student.class)
                .block(); // blocks the stream and get data out of it
    }
}
