package org.niit.vehicleapp.controller;

import org.niit.vehicleapp.model.Product;
import org.niit.vehicleapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.FOUND);
    }
    @GetMapping("/get-product-by-id/{vehicleId}")
    public ResponseEntity<?> getProductById(@PathVariable String vehicleId){
        return new ResponseEntity<>(productService.getProductDetails(vehicleId),HttpStatus.FOUND);
    }
    @PostMapping("/admin/add-new-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product, HttpServletRequest request) throws ServletException {
        String role = (String) request.getAttribute("curr_user_role");
        if(!role.equals("admin")) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(productService.addProductToList(product),HttpStatus.CREATED);
    }
    @PutMapping("/admin/update-product/{vehicleId}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable String vehicleId, HttpServletRequest request){
        String role = (String) request.getAttribute("curr_user_role");
        if(!role.equals("admin")) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(productService.updateProductToList(product,vehicleId),HttpStatus.OK);
    }
    @DeleteMapping("/admin/delete-product/{vehicleId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String vehicleId, HttpServletRequest request){
        String role = (String) request.getAttribute("curr_user_role");
        if(!role.equals("admin")) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(productService.deleteProductById(vehicleId),HttpStatus.OK);
    }
}
