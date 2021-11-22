package com.VPI.VPI.Security.Jwt;

import com.VPI.VPI.Security.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_kEY = "secret";


    public String extractUsername(String token){
        return  extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = estratcAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims estratcAllClaims(String token){
        return  Jwts.parser().setSigningKey(SECRET_kEY).parseClaimsJws(token).getBody();
    }


    public String generateToken(Authentication authentication){
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return  Jwts.builder().setSubject(myUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_kEY).compact();
    }

   public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);

   }

   private Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
   }
   private String createToken(Map<String,Object> claims, String subject){
       return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
               .signWith(SignatureAlgorithm.HS256, SECRET_kEY).compact();
   }

   public Boolean validateToken(String token, UserDetails userDetails){
       final String username = extractUsername(token);
       return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
    public Boolean validateToken2(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_kEY).parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException e){

        }
        return false;



    }

}
