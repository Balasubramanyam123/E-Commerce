package org.niit.vehicleapp.service;

import org.niit.vehicleapp.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductDetails(String vehicleId);
    public Product addProductToList(Product product);
    public Product updateProductToList(Product product,String vehicleId);
    public boolean deleteProductById(String vehicleId);
}
