package com.over.snowshop.services;

import com.over.snowshop.dao.CategoryDao;
import com.over.snowshop.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDao categoryDao;

    @Override
    @Transactional
    public List getSections() {
        return categoryDao.getSections();
    }

    @Override
    @Transactional
    public Category getCurrentCategory(Long categoryId) {
        return categoryDao.getCurrentCategory(categoryId);
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
