package com.alejandro.ana.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {

    @Bean
    public SimpleMailMessage emailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("alejandrosubero53@gmail.com");
        message.setFrom("blusubero@gmail.com");
        message.setSubject("Important email");
        message.setText("GENERATE THE ONE PROYECT !!");
        return message;
    }
}