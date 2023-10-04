package org.niit.userauthenticationapp.service;

import org.niit.userauthenticationapp.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);
    public List<User> getAllUsers();
    public User updateUser(User user,String email);
    public boolean deleteUserByEmail(String email);
    public User loginCheck(String email, String password);
}
