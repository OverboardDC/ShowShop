package com.over.snowshop.services;

import com.over.snowshop.dao.BrandDao;
import com.over.snowshop.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandDao brandDao;

    @Override
    @Transactional
    public List getBrandsByCategory(Long categoryId, Long brandId) {
        return brandDao.getBrandsByCategory(categoryId, brandId);
    }

    @Override
    @Transactional
    public List<Brand> getBrandsBySearch(String query, Long chosenBrandId) {
        return brandDao.getBrandsBySearch(query, chosenBrandId);
    }

    @Override
    @Transactional
    public List getMainBrands() {
        return brandDao.getMainBrands();
    }

    @Override
    @Transactional
    public Brand getCurrentBrand(Long brandId) {
        return brandDao.getCurrentBrand(brandId);
    }

    @Override
    @Transactional
    public byte[] getBrandLogo(Long brandId) {
        return brandDao.getBrandLogo(brandId);
    }

    @Autowired
    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }
}
