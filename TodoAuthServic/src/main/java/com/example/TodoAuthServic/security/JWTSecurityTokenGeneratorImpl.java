package com.example.TodoAuthServic.security;



import com.example.TodoAuthServic.Domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public  Map<String,String>  createToken(User user){
        // Write logic to create the Jwt
        Map<String,Object> claims=new HashMap<>();
        claims.put("userEmail",user.getUserEmail());
       return generateToken(claims,user.getUserEmail());
    }


    public  Map<String,String>  generateToken(Map<String,Object> claims,String subject) {

        // Generate the token and set the user id in the claims
         String jwtToken = Jwts.builder()
                 .setClaims(claims)
                 .setSubject(subject)
                 .setIssuedAt(new Date())
                 .signWith(SignatureAlgorithm.HS256,"password").compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("success", String.valueOf(1));
         return map;

    }


}
