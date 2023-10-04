package org.niit.vehicleapp.controller;

import org.niit.vehicleapp.model.Product;
import org.niit.vehicleapp.model.User;
import org.niit.vehicleapp.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/vehicle-app")
public class UserProductController {
    UserProductService userProductService;

    @Autowired
    public UserProductController(UserProductService userProductService) {
        this.userProductService = userProductService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>(userProductService.registerUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/get-user-details")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request){
        String email = (String) request.getAttribute("curr_user_email");
        return new ResponseEntity<>(userProductService.getUserById(email),HttpStatus.OK);
    }
    @PostMapping("/add-product-to-user")
    public ResponseEntity<?> addProduct(@RequestBody Product product,HttpServletRequest request){
        String emailId = (String) request.getAttribute("curr_user_email");
        return new ResponseEntity<>(userProductService.addProductToUserList(product,emailId),HttpStatus.OK);
    }
    @DeleteMapping("/delete-product-from-user-cart/{vehicleId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String vehicleId,HttpServletRequest request){
        String emailId = (String) request.getAttribute("curr_user_email");
        return new ResponseEntity<>(userProductService.deleteProductToUserList1 (emailId, vehicleId),HttpStatus.OK);
    }
}
