package com.over.snowshop.services;

import com.over.snowshop.dao.FilterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FilterServiceImpl implements FilterService {

    private FilterDao filterDao;

    @Override
    @Transactional
    public List getFiltersByCategory(Long categoryId, Map<String, Long> lastFilters) {
        return filterDao.getFiltersByCategory(categoryId, lastFilters);
    }

    @Autowired
    public void setFilterDao(FilterDao filterDao) {
        this.filterDao = filterDao;
    }
}
