package com.danielsolawa.spaceflight;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpaceflightApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceflightApplication.class, args);
    }



}
