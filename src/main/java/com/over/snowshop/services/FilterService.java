package com.over.snowshop.services;

import java.util.List;
import java.util.Map;

public interface FilterService {

    List getFiltersByCategory(Long categoryId, Map<String, Long> lastFilters);
}
