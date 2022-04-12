package uz.psy.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.psy.demo.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final String secretKey = "secretShit";

    public String generateToken(String username, Set<Role> roles){

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    public String getPhoneNumberFromToken(String token){
        try {


            String phoneNumber = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return phoneNumber;
        }catch (Exception e){
            return null;
        }
    }

}
