package org.niit.userauthenticationapp.service;

import org.niit.userauthenticationapp.exception.CustomerAlreadyExistsException;
import org.niit.userauthenticationapp.exception.CustomerNotFoundException;
import org.niit.userauthenticationapp.feignclient.SignupData;
import org.niit.userauthenticationapp.feignclient.UserDto;
import org.niit.userauthenticationapp.feignclient.UserProxy;
import org.niit.userauthenticationapp.model.User;
import org.niit.userauthenticationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    UserRepository userRepository;
    UserProxy userProxy;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }


    @Override
    public User addUser(User user) throws CustomerAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new CustomerAlreadyExistsException();
        }
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
    public User loginCheck(String email, String password) throws CustomerNotFoundException {
        if(userRepository.findById(email).isPresent()){
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(password)){
                return user;
            }
        }else{
            throw new CustomerNotFoundException();
        }
        return null;
    }

    @Override
    public User registerUser1(SignupData signupData) throws CustomerAlreadyExistsException {
        UserDto userDto = new UserDto(signupData.getEmail(), signupData.getPassword(), signupData.getFirstName(), signupData.getLastName(), signupData.getRole());
        ResponseEntity responseEntity = userProxy.sendUserDtoToVehicleApp(userDto);
        User user = new User(signupData.getEmail(), signupData.getPassword(), signupData.getFirstName(), signupData.getLastName(), signupData.getRole());
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new CustomerAlreadyExistsException();
        }
        return userRepository.save(user);
    }
}
