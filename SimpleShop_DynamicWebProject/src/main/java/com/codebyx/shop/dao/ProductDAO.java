package com.codebyx.shop.dao;

import java.util.List;

import com.codebyx.shop.entity.Product;

public interface ProductDAO {
    List<Product> findAll() throws Exception;

    Product findById(int id) throws Exception;
}
