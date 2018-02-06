package com.over.snowshop.objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class SortingTypesMap {

    private static final Map<String, SortingType> map = new LinkedHashMap<>();

    static {
        map.put("featured", new SortingType("Featured"));
        map.put("highest_rated", new SortingType("Highest Rated"));
        map.put("new_arrivals", new SortingType("New Arrivals"));
        map.put("highest_price", new SortingType("Highest Price"));
        map.put("lowest_price", new SortingType("Lowest Price"));
    }

    public static Map<String, SortingType> getMap() {
        return map;
    }
}
