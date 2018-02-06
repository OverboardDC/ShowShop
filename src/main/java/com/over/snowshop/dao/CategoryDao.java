package com.over.snowshop.dao;

import com.over.snowshop.entities.Category;

import java.util.List;

public interface CategoryDao {

    List getSections();

    Category getCurrentCategory(Long categoryId);

}
