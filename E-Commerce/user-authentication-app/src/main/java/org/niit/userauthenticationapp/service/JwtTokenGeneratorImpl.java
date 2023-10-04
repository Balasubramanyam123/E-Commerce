package org.niit.userauthenticationapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.niit.userauthenticationapp.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator{
    @Override
    public Map<String, String> generateJwt(User user) {
        Map<String,String> result = new HashMap<String,String>();
        Map<String,Object> userdata = new HashMap<>();
        userdata.put("email",user.getEmail());
        userdata.put("firstName",user.getFirstName());
        userdata.put("role",user.getRole());

        String jwt = Jwts.builder().setClaims(userdata).setIssuedAt(new Date()).setIssuer("MyCompany").signWith(SignatureAlgorithm.HS512,"SecurityKey").compact();
        result.put("token",jwt);
        result.put("message","Login success,Token generated");
        result.put("role", user.getRole());
        return result;
    }
}
