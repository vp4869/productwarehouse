package com.comcast.productwarehouse.repo;

import com.comcast.productwarehouse.productmodel.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findProductByCategoryContainingOrderByProductName(String category);
}
