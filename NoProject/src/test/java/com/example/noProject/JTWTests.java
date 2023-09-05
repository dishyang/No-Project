package com.example.noProject;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.HashMap;



@SpringBootTest
@Slf4j
public class JTWTests {



    @Test
    void createJWT() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        HashMap<String, Object> claimMap = new HashMap<>();
        claimMap.put("id", 1);
        claimMap.put("name", "noProject");
        claimMap.put("email", "noProject@gmail.com");
        claimMap.put("role", "admin");


        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3))
                .setClaims(claimMap)
                .compressWith(new GzipCompressionCodec())
                .signWith(secretKey);

        String jws = jwtBuilder.compact();

        try{
            Jws<Claims> parseClaimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .setClock(() -> new Date())
                    .build()
                    .parseClaimsJws(jws);
            parseClaimsJws.getHeader();
            Claims body = parseClaimsJws.getBody();
            System.out.println(body.get("name"));

        }catch (JwtException e){
            System.out.println("JWT不被信任");
        }


    }

    @Test
    void parserJWT() {
        String jws = "eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKsrPSVWyUkpMyc3MU9JRykvMBXHz8gOK8rNSk0uAQpkpSlaGOkqpuYmZOchSDukgEb3k_FylWgArolMsSAAAAA.TtC5EvD0wQgzB5OEDY6qLMpzhmoOyJ53HX-LvrqcMMc";

        try{
            Jws<Claims> parseClaimsJws = Jwts.parserBuilder()
                    .setSigningKey("sdfsdfsdfa4sd5f456as4d56f456as4f56")
                    .setClock(() -> new Date())
                    .build()
                    .parseClaimsJws(jws);
            parseClaimsJws.getHeader();
            Claims body = parseClaimsJws.getBody();
            System.out.println(body.get("name"));

        }catch (JwtException e){
            log.debug(e.getMessage());
            System.out.println("JWT不被信任");
        }
    }



    /**
     * 非对称加密
     */
    @Test
    void createJWT2() {

        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        log.debug("公钥：{}", keyPair.getPublic());
        log.debug("私钥：{}", keyPair.getPrivate());
        HashMap<String, Object> claimMap = new HashMap<>();
        claimMap.put("id", 1);
        claimMap.put("name", "noProject");
        claimMap.put("email", "noProject@gmail.com");
        claimMap.put("role", "admin");


        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3))
                .setClaims(claimMap)
                .compressWith(new GzipCompressionCodec())
                .signWith(keyPair.getPrivate());

        String jws = jwtBuilder.compact();
        log.debug("jws:{}", jws);

        try{
            Jws<Claims> parseClaimsJws = Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .setClock(() -> new Date())
                    .build()
                    .parseClaimsJws(jws);
            parseClaimsJws.getHeader();
            Claims body = parseClaimsJws.getBody();
            System.out.println(body.get("name"));

        }catch (JwtException e){
            System.out.println("JWT不被信任");
        }
    }

    /**
     * 非对称解密
     */
    @Test
    void parseJwt() {
        try {
            PrivateKey rsaPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(
                    new BigInteger("29943534683943105030738495544323878356922956434385257613235446359648728167032319079153152011701632739202728844230726906752279525563954226006884009640499095197606953841519803020000472920610315385356198499620899135374452584530195558187294814662861040155147064353910628550534223239898311982696621181378330603751914459706009016146571888952270725397134245156045972651569210390843108864108029187569160553832198029935572933363329810442516409029856447477638246309539900583776564760604756162748599403782398544333378994692512842158622400883464974801025914878689423859949580324919320417779244116390045517544412905723050197285801"),
                    new BigInteger("538614061439445335598111568888589965976314876648844264252949595233709430952527791370135104489638074378706288972187920966171368071152283166126020083646094209749896095989880121198807876337851295691113089920571933553586921951489068870935625918179107774147806528151971965234487846942819368080435518406688444569279168408937544862160170406124777498684434174917817470230342453496991490512303005699109987152317197687556478594811412361570017476714097210381760326694659991657390966761769590168432730819862563597013843028268971115235420575008863337781013992991438152627657016793839804692403747401022507968626741388877137253585")
            ));
            PublicKey rsaPublice = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(
                    new BigInteger("29943534683943105030738495544323878356922956434385257613235446359648728167032319079153152011701632739202728844230726906752279525563954226006884009640499095197606953841519803020000472920610315385356198499620899135374452584530195558187294814662861040155147064353910628550534223239898311982696621181378330603751914459706009016146571888952270725397134245156045972651569210390843108864108029187569160553832198029935572933363329810442516409029856447477638246309539900583776564760604756162748599403782398544333378994692512842158622400883464974801025914878689423859949580324919320417779244116390045517544412905723050197285801"),
                    new BigInteger("65537")
            ));

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(rsaPublice)
                    .build()
                    .parseClaimsJws("eyJhbGciOiJSUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKsrPSVWyUkpMyc3MU9JRykvMBXHz8gOK8rNSk0uAQpkpSlaGOkqpuYmZOchSDukgEb3k_FylWgArolMsSAAAAA.yx9NnwZJkghMLG1Y0Cru3J71z8z8G9wZs0h-WvMhlZc0ozqQfm65SBx2MYB_rWZaRJGZy8wXMrIfTBe0PEHL2818YBHMtFXeSBvGkwaDsmqz9-CA_Y5y08o0p8xqvtrryFZzmuo7bzLkZl3IKE2SctopqscpiSAOBC_X1I5sVfb_1cF3OMHVxs2uEWFIfJx0aKFR-HBwgjLA8577HrnUJhZIbx9p2c_fn5Knu0jwYB-fxvBL3IkFHjpB6k76NsGdLvZmuky7FGcjZUFS439SH8h_FQ3at_l2A1gWdEgxcampNC39J8yYetr7UjUX3MTEgxopv7oynL1TUH1iIy3dzQ");

            log.info("claimsJws: {}", claimsJws.getHeader());

        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }catch (JwtException e){
            System.out.println("JWT不被信任");
        }
    }

    /**
     * 验证jwt
     */
    @Test
    void yanzheng() {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .build()
                    .parse("eyJhbGciOiJSUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKsrPSVWyUkpMyc3MU9JRykvMBXHz8gOK8rNSk0uAQpkpSlaGOkqpuYmZOchSDukgEb3k_FylWgArolMsSAAAAA.yx9NnwZJkghMLG1Y0Cru3J71z8z8G9wZs0h-WvMhlZc0ozqQfm65SBx2MYB_rWZaRJGZy8wXMrIfTBe0PEHL2818YBHMtFXeSBvGkwaDsmqz9-CA_Y5y08o0p8xqvtrryFZzmuo7bzLkZl3IKE2SctopqscpiSAOBC_X1I5sVfb_1cF3OMHVxs2uEWFIfJx0aKFR-HBwgjLA8577HrnUJhZIbx9p2c_fn5Knu0jwYB-fxvBL3IkFHjpB6k76NsGdLvZmuky7FGcjZUFS439SH8h_FQ3at_l2A1gWdEgxcampNC39J8yYetr7UjUX3MTEgxopv7oynL1TUH1iIy3dzQ");
        }catch (JwtException e){
            System.out.println("JWT不被信任");
        }
    }


    @Test
    void parserJwt() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        HashMap<String, Object> claimMap = new HashMap<>();
        claimMap.put("id", 1);
        claimMap.put("name", "noProject");
        claimMap.put("email", "noProject@gmail.com");
        claimMap.put("role", "admin");


        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3))
                .setClaims(claimMap)
                .compressWith(new GzipCompressionCodec())
                .signWith(secretKey);
        String jws = jwtBuilder.compact();

        try{
            Jwt jwt = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .setClock(() -> new Date())
                    .requireSubject("sdf")
                    .build()
                    .parse(jws);
            System.out.println(jwt);
        }catch (JwtException e){
            log.error("JWT不被信任: {}",e.getMessage());
        }

    }
}
