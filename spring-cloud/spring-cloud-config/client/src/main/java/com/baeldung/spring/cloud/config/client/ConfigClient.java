package com.baeldung.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClient {
    @Value("${user.role}")
    private String role;

    @Value("${user.password}")
    private String password;

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }

    @RequestMapping(value = "/whoami/{username}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String whoami(@PathVariable("username") String username) {
        //return String.format("Hello %s! You are a(n) %s and your password is '%s'.\n", username, role, password);
        return String.format("Hello! You're %s and you'll become a(n) %s, " +
          "but only if your password is '%s'!\n", username, role, password);
    }

    @RequestMapping(value = "/showcredentials", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String showcredentials() {
        return String.format("The aws.accessKeyId is %s and the aws.secretKey is %s\n", accessKey, secretKey);
    }
}
