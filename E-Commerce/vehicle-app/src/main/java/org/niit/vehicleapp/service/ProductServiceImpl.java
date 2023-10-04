package org.niit.vehicleapp.service;

import org.niit.vehicleapp.model.Product;
import org.niit.vehicleapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductDetails(String vehicleId) {
        return productRepository.findById(vehicleId).get();
    }

    @Override
    public Product addProductToList(Product product) {
        if(productRepository.findById(product.getVehicleId()).isPresent()){
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProductToList(Product product, String vehicleId) {
        Optional<Product> optionalProduct = productRepository.findById(vehicleId);
        if(optionalProduct.isEmpty()){
            return null;
        }
        Product existingProduct = optionalProduct.get();
        if(product.getType()!=null){
            existingProduct.setType(product.getType());
        }
        if(product.getVehicleName()!=null){
            existingProduct.setVehicleName(product.getVehicleName());
        }
        if(product.getPrice()!=null){
            existingProduct.setPrice(product.getPrice());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public boolean deleteProductById(String vehicleId) {
        if(productRepository.findById(vehicleId).isEmpty()){
            return false;
        }
        productRepository.deleteById(vehicleId);
        return true;
    }
}
