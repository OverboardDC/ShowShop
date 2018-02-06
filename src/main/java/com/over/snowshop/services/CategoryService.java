package com.over.snowshop.services;

import com.over.snowshop.entities.Category;
import com.over.snowshop.entities.Section;

import java.util.List;

public interface CategoryService {

    List getSections();

    Category getCurrentCategory(Long categoryId);
}
