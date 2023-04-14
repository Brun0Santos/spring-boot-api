package com.bruno.api.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Base64;
import java.util.Date;

public class TesteUser {

    public static void main(String[] args) {
        String textoOriginal = "Começando a enteder o funcionamento do base 64";
        System.out.println("Texto original" + textoOriginal);

        String textoSerializado = Base64.getEncoder().encodeToString(textoOriginal.getBytes());

        System.out.println("Texto serializado " + textoSerializado);

        String textoDeserializado = new String(Base64.getDecoder().decode(textoSerializado));

        System.out.println("Texto deserializado " + textoDeserializado);

        Algorithm algorithm = Algorithm.HMAC256(textoDeserializado.getBytes());

        System.out.println(algorithm);

        long validityInMilliseconds = 3600000;
        Date now = new Date();

        Date validity = new Date(now.getTime() + validityInMilliseconds);
        System.out.println(validity);
        System.out.println(now.getTime());
        String message = " piiiiiii eu nem sei como escrever ";
        System.out.println(message);
        System.out.println(message.strip());
        String grandeTexto = "Bearer 2323123313434sdfsdfwfwefwefwefw";
        System.out.println(grandeTexto.substring("Bearer".length()).strip());
        System.out.println(grandeTexto.substring(7));

        String secretKey = "your-256-bit-secret";
        Algorithm algorithm1 = null;


//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//
//        algorithm1 = Algorithm.HMAC256(secretKey.getBytes());
//        System.out.println(algorithm1);

        String encodedJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT verifyer = verifier.verify(encodedJWT);

        System.out.println(verifyer.getSubject());

    }

}