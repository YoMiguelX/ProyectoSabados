package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final Environment env; // opcional para armar URLs

    public EmailService(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    public void sendPasswordResetEmail(String to, String token) {
        String appUrl = env.getProperty("app.frontend.url", "http://localhost:3000"); // front-end donde el usuario pone el token
        String resetLink = appUrl + "/reset-password?token=" + token;

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Restablecer contraseña - EduGame");
        msg.setText("Hola,\n\nRecibimos una solicitud de restablecimiento de contraseña. " +
                "Usa este enlace para cambiar tu contraseña (válido 15 min):\n\n" + resetLink +
                "\n\nSi no pediste esto, ignora este correo.");
        mailSender.send(msg);
        System.out.println("sebastian haga el email sevice");
    }




}
