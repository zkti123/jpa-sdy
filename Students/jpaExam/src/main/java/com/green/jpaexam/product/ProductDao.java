package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    ProductRes saveProduct(ProductEntity p);

    List<ProductRes> getProductAll();

    ProductRes getProduct(Long number);

    ProductRes updProduct(ProductEntity product);

    void delProduct(Long number);

}
