package com.altimetric.sso.config;

import com.altimetric.sso.exception.UnAuthorizedException;
import com.altimetric.sso.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;

@Configuration
@Slf4j
public class JWTVerifier {

    @Value("${secret}")
    private String secretKey;

    @Value("${tokenExpiryInMilliSeconds}")
    private long tokenExpiryInMilliSeconds;


    public Claims extractDataFromToken(String token) throws UnAuthorizedException {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token Expired", e);
            throw new UnAuthorizedException("Token Expired");
        } catch (SignatureException e) {
            log.error("Invalid Token Signature", e);
            throw new UnAuthorizedException("Invalid Token Signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid Token ", e);
            throw new UnAuthorizedException("Invalid Token");
        }
    }

    public String generateToken(User user) {
        log.info("generating new token");
        HashMap<String, Object> claims = new HashMap();
        claims.put("userId", user.getEmail());
        claims.put("email", user.getPassword());

        return Jwts.builder().addClaims(claims).
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiryInMilliSeconds)).
                signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}
