package com.comcast.productwarehouse.service;

import com.comcast.productwarehouse.productmodel.Product;
import com.comcast.productwarehouse.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final ProductRepo prodRepo;

    @Autowired
    public WarehouseService(ProductRepo prodRepo) {
        this.prodRepo = prodRepo;
    }

    public Boolean addProduct(Product prod){
        if(prodRepo.existsById(prod.getId()))
            return false;
        else{
            prodRepo.save(prod);
            return true;
        }
    }

    public List<Product> findAllProducts(){
        return prodRepo.findAll();
    }

    public Optional<Product> findProductById(Long id){
        return prodRepo.findById(id);
    }

    public List<Product> findProductByCategory(String category){
        return prodRepo.findProductByCategoryContainingOrderByProductName(category);
    }

    public Boolean updateProduct(Product prod){
        if(prodRepo.existsById(prod.getId())){
            prodRepo.save(prod);
            return true;
        }
        else
            return false;
    }

    public void deleteProductById(Long id){
        prodRepo.delete(prodRepo.getById(id));
    }
}
