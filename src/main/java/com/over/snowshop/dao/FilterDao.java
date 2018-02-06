package com.over.snowshop.dao;

import java.util.List;
import java.util.Map;

//Unused
public interface FilterDao {

    List getFiltersByCategory(Long categoryId, Map<String, Long> lastFilters);
}
