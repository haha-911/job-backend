package com.milk.job.common.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-11 14:36
 */
public class TokenUtils {

    private static  Long tokenExpiration= 24  * 60 * 60 *1000L ;

    private static final String SING_KEY="auth-user";

    public static String createToken(String username,Integer userId){

        return Jwts.builder()
                .claim("username",username)
                .claim("userId",userId)
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .signWith(SignatureAlgorithm.HS256,SING_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();

    }

    public static Integer getUserId(String token){

        try {
            if (StringUtils.isEmpty(token)){
                return null;
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SING_KEY).parseClaimsJws(token);
            Integer userId = (Integer)claimsJws.getBody().get("userId");

            return userId;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getUsername(String token){

        try {
            if (StringUtils.isEmpty(token)){
                return null;
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SING_KEY).parseClaimsJws(token);
            return  (String)claimsJws.getBody().get("username");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        String token = "eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi1OLcpLzE1VslIqys8vUdIBi3imKFkZ6iilVhQAaTNzY1MDCwsjg1oAFvwhmi8AAAA.rKWH9VCTG9rpG3KYEb3ATBWrJ2UZAzHCqwsp9MnMvBA";
        Integer userId = getUserId(token);

        System.out.println(userId);
    }


}
