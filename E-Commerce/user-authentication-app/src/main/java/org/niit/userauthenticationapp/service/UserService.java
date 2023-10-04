package org.niit.userauthenticationapp.service;

import org.niit.userauthenticationapp.exception.CustomerAlreadyExistsException;
import org.niit.userauthenticationapp.exception.CustomerNotFoundException;
import org.niit.userauthenticationapp.feignclient.SignupData;
import org.niit.userauthenticationapp.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user) throws CustomerAlreadyExistsException;
    public List<User> getAllUsers();
    public User updateUser(User user,String email);
    public boolean deleteUserByEmail(String email);
    public User loginCheck(String email, String password) throws CustomerNotFoundException;
    public abstract User registerUser1(SignupData signupData) throws CustomerAlreadyExistsException;
}
