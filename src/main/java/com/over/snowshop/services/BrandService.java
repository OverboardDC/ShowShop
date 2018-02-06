package com.over.snowshop.services;

import com.over.snowshop.entities.Brand;

import java.util.List;

public interface BrandService {

    List getBrandsByCategory(Long categoryId, Long brandId);

    List<Brand> getBrandsBySearch(String query, Long chosenBrandId);

    List getMainBrands();

    Brand getCurrentBrand(Long brandId);

    byte[] getBrandLogo(Long brandId);
}
