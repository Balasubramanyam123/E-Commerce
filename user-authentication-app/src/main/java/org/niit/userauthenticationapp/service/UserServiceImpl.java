package org.niit.userauthenticationapp.service;

import org.niit.userauthenticationapp.model.User;
import org.niit.userauthenticationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, String email) {
        Optional<User> userOptional =userRepository.findById(email);
        if(userOptional.isEmpty())
        {
            return null;
        }
        User existingUser = userOptional.get();
        if(user.getFirstName()!=null){
            existingUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            existingUser.setLastName(user.getLastName());
        }
        if(user.getPassword()!=null){
            existingUser.setPassword(user.getPassword());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        if(userRepository.findById(email).isEmpty())
            return false;
        else {
            userRepository.deleteById(email);
            return true;
        }
    }

    @Override
    public User loginCheck(String email, String password) {
        if(userRepository.findById(email).isPresent()){
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(password)){
                return user;
            }
        }else{
            return null;
        }
        return null;
    }
}
