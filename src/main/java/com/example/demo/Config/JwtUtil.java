package com.example.demo.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generarToken(String correo, Integer rolId) {
        return Jwts.builder()
                .setSubject(correo)
                .claim("rol", rolId) // 👈 guardamos el rol en el token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // expira en 1 día
                .signWith(key)
                .compact();
    }
}
