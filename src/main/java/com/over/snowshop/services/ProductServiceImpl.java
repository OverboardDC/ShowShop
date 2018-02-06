package com.over.snowshop.services;

import com.over.snowshop.dao.ProductDao;
import com.over.snowshop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Override
    @Transactional
    public Product getProduct(Long id) {
        return productDao.getProduct(id);
    }

    @Override
    @Transactional
    public List getProductsByCategory(Long categoryId, String sortType, Long brandId, Integer page) {
        return productDao.getProductsByCategory(categoryId, sortType, brandId, page);
    }

    @Override
    @Transactional
    public List getProductsByBrand(Long brandId, String sortType, Integer page) {
        return productDao.getProductsByBrand(brandId, sortType, page);
    }

    @Override
    @Transactional
    public List getProductBySearch(String query, String sortType, Long brandId, Integer page) {
        return productDao.getProductBySearch(query, sortType, brandId, page);
    }

    @Override
    @Transactional
    public List getLatestProducts() {
        return productDao.getLatestProducts();
    }

    @Override
    @Transactional
    public List getBestsellers() {
        return productDao.getBestsellers();
    }

    @Override
    @Transactional
    public byte[] getProductImage(Long productId) {
        return productDao.getImage(productId);
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
