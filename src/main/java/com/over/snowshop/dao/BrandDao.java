package com.over.snowshop.dao;

import com.over.snowshop.entities.Brand;

import java.util.List;

public interface BrandDao {

    List<Brand> getBrandsByCategory(Long categoryId, Long chosenBrandId);

    List<Brand> getBrandsBySearch(String query, Long chosenBrandId);

    List getMainBrands();

    Brand getCurrentBrand(Long brandId);

    byte[] getBrandLogo(Long brandId);
}
