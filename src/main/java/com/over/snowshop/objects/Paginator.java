package com.over.snowshop.objects;

import java.util.LinkedList;
import java.util.List;

public class Paginator {

    private static Long rowsCount;
    private static int productsOnPage = 2;

    public static List getPages(int page) {
        LinkedList<Integer> pages = new LinkedList<>();
        int limit;
        if (rowsCount % 2 == 0) {
            limit = rowsCount.intValue() / productsOnPage;
        } else {
            limit = rowsCount.intValue() / productsOnPage + 1;
        }
        for (int i = 1; i <= limit; i++) {
            if(pages.size() < 5) {
                pages.add(i);
            } else {
                if(page > pages.getLast() / 2 + 1){
                    pages.removeFirst();
                    pages.add(i);
                }
            }
        }
        return pages;
    }

    public static Long getRowsCount() {
        return rowsCount;
    }

    public static void setRowsCount(Long rowsCount) {
        Paginator.rowsCount = rowsCount;
    }

    public static int getProductsOnPage() {
        return productsOnPage;
    }

    public static void setProductsOnPage(int productsOnPage) {
        Paginator.productsOnPage = productsOnPage;
    }
}
