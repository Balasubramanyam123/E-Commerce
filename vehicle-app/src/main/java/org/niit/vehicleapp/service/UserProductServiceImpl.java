package org.niit.vehicleapp.service;

import org.niit.vehicleapp.model.Product;
import org.niit.vehicleapp.model.User;
import org.niit.vehicleapp.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService{
    UserProductRepository userProductRepository;

    @Autowired
    public UserProductServiceImpl(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }

    @Override
    public User registerUser(User user) {

        if(userProductRepository.findById(user.getEmail()).isPresent())
        {
            return null;
        }
        return userProductRepository.insert(user);

    }

    @Override
    public User getUserById(String email) {
        return userProductRepository.findById(email).get();
    }

    @Override
    public User addProductToUserList(Product product, String email) {
        if (userProductRepository.findById(email).isEmpty()){
            return null;
        }
        User result =userProductRepository.findById(email).get();
        if (result.getProductList() == null){
            result.setProductList(Arrays.asList(product));
        }else{
            List<Product> products =result.getProductList();
            products.add(product);
            result.setProductList(products);
        }
        userProductRepository.save(result);
        return result;
    }
}
