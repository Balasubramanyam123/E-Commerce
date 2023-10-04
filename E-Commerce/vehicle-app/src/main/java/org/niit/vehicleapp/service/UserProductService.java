package org.niit.vehicleapp.service;

import org.niit.vehicleapp.model.Product;
import org.niit.vehicleapp.model.User;

import java.util.List;

public interface UserProductService {
    public User registerUser(User user);
//    public List<Product> getAllProducts();
    public User getUserById(String email);
    public User addProductToUserList(Product product, String email);
    public User deleteProductToUserList1(String email,String vehicleId);
}
