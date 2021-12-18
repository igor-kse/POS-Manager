package ru.posmanager.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class JsonWebTokenUtil {
    private static final Properties properties = new Properties();
    static {
        initProperties("token.properties");
    }
    @Getter
    private static final byte[] secret = properties.getProperty("secret").getBytes(StandardCharsets.UTF_8);
    @Getter
    private static final String issuer = properties.getProperty("issuer");
    @Getter
    private static final String audience = properties.getProperty("audience");
    @Getter
    private static final String signatureAlgorithm = properties.getProperty("signatureAlgorithm");
    @Getter
    private static final String userId = properties.getProperty("user.id");
    @Getter
    private static final String adminId = properties.getProperty("admin.id");
    @Getter
    private static final String userLifeTime = properties.getProperty("user.lifeTimeSec");
    @Getter
    private static final String adminLifeTime = properties.getProperty("admin.lifeTimeSec");

    public static String generateJsonWebToken(String subject, boolean admin) {
        String id = admin ? adminId : userId;
        long lifeTime = Long.parseLong(admin ? adminLifeTime : userLifeTime);

        return Jwts.builder()
                .setId(id)
                .setIssuer(issuer)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(lifeTime)))
                .signWith(SignatureAlgorithm.valueOf(signatureAlgorithm), Base64.getEncoder().encode(secret))
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secret))
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isValid(String token) {
        return !isExpired(token);
    }

    public static boolean isValid(String token, String username) {
        return isValid(token) && getSubject(token).equals(username);
    }

    public static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public static String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private static void initProperties(String path) {
        try {
            InputStream is = JsonWebTokenUtil.class.getClassLoader().getResourceAsStream(path);
            properties.load(is);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}