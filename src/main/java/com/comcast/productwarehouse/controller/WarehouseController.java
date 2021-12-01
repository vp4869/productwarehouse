package com.comcast.productwarehouse.controller;

import com.comcast.productwarehouse.productmodel.Product;
import com.comcast.productwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseServe;

    @Autowired
    public WarehouseController(WarehouseService warehouseServe) {
        this.warehouseServe = warehouseServe;
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product prod){
        if(warehouseServe.addProduct(prod))
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product prod){
        if(warehouseServe.updateProduct(prod))
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity findAllProducts(){
        return new ResponseEntity(warehouseServe.findAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findProductById(@PathVariable Long id){
        Optional<Product> product = warehouseServe.findProductById(id);
        if(product.isPresent()){
            return new ResponseEntity(product.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity(new Product(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/category/{category}")
    public ResponseEntity findProductByCategory(@PathVariable String category){
        return new ResponseEntity(warehouseServe.findProductByCategory(category),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id){
        warehouseServe.deleteProductById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}

