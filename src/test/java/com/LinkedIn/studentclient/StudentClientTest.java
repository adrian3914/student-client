package com.LinkedIn.studentclient;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.linkedIn:student-service:+:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class StudentClientTest {

    // Student client that talks to student service
    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_forGivenStudent_isReturned(){
        // given
        Long id = 1l;
        stubFor(get("/students/" + id).willReturn(okJson("""
            {
            "id":1,
            "studentName": "Mark",
            "grade": 10
            }
            """
        )));

        // when
        Student student = studentClient.getStudent(id);

        // then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Mark");
        then(student.getGrade()).isEqualTo(10);
    }
}