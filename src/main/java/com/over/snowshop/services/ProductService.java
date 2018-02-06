package com.over.snowshop.services;

import com.over.snowshop.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product getProduct(Long id);

    List getProductsByCategory(Long categoryId, String sortType, Long brandId, Integer page);

    List getProductsByBrand(Long brandId, String sortType, Integer page);

    List getProductBySearch(String query, String sortType, Long brandId, Integer page);

    List getLatestProducts();

    List getBestsellers();

    byte[] getProductImage(Long productId);
}
