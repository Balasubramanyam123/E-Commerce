package org.niit.userauthenticationapp.service;

import org.niit.userauthenticationapp.model.User;

import java.util.Map;

public interface JwtTokenGenerator {
    public abstract Map<String,String> generateJwt(User user);
}
