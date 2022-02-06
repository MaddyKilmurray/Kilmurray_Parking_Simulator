package com.kilmurray.trigg_java_developer_challenge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
public class TriggJavaDeveloperChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriggJavaDeveloperChallengeApplication.class, args);
    }

}
