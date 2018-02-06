package com.over.snowshop.controllers;

import com.over.snowshop.entities.Brand;
import com.over.snowshop.objects.Paginator;
import com.over.snowshop.objects.SortingTypesMap;
import com.over.snowshop.services.BrandService;
import com.over.snowshop.services.CategoryService;
import com.over.snowshop.services.FilterService;
import com.over.snowshop.services.ProductService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private BrandService brandService;

    @ModelAttribute
    public void submitAttributes(Model model, HttpSession session) {
        model.addAttribute("sections", categoryService.getSections());
        model.addAttribute("sortingTypes", SortingTypesMap.getMap());
        model.addAttribute("cart", session.getAttribute("cart"));
    }

    @RequestMapping(value = "/products/{id}")
    public String getProductsByCategory(@PathVariable("id") Long categoryId,
                                             @RequestParam(name = "sort", defaultValue = "new_arrivals") String sortType,
                                             @RequestParam(name = "brand", required = false) Long brandId,
                                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             Model model) {

        model.addAttribute("products", productService.getProductsByCategory(categoryId, sortType, brandId, page));
        model.addAttribute("currentCategory", categoryService.getCurrentCategory(categoryId));
        model.addAttribute("brands", brandService.getBrandsByCategory(categoryId, brandId));
        model.addAttribute("pages", Paginator.getPages(page));
        model.addAttribute("lastSort", sortType);
        model.addAttribute("lastBrand", brandId);
        model.addAttribute("lastChosenPage", page);
        return "products";
    }

    @RequestMapping(value = "/brand/products/{id}")
    public String getProductsByBrand(@PathVariable("id") Long brandId,
                                             @RequestParam(name = "sort", defaultValue = "new_arrivals") String sortType,
                                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             Model model) {

        model.addAttribute("products", productService.getProductsByBrand(brandId, sortType, page));
        model.addAttribute("currentCategory", brandService.getCurrentBrand(brandId));
        model.addAttribute("brands", null);
        model.addAttribute("pages", Paginator.getPages(page));
        model.addAttribute("lastSort", sortType);
        model.addAttribute("lastBrand", brandId);
        model.addAttribute("lastChosenPage", page);
        return "products";
    }

    @RequestMapping(value = "/search/products/")
    public String getProductsBySearch(@RequestParam("query") String query,
                                        @RequestParam(name = "sort", defaultValue = "new_arrivals") String sortType,
                                        @RequestParam(name = "brand", required = false) Long brandId,
                                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                                        Model model) {

        model.addAttribute("products", productService.getProductBySearch(query, sortType, brandId, page));
        model.addAttribute("currentCategory", categoryService.getCurrentCategory(null));
        model.addAttribute("brands", brandService.getBrandsBySearch(query, brandId));
        model.addAttribute("pages", Paginator.getPages(page));
        model.addAttribute("lastSort", sortType);
        model.addAttribute("lastBrand", brandId);
        model.addAttribute("lastChosenPage", page);
        model.addAttribute("lastQuery", query);
        return "products";
    }

    @RequestMapping(value = "/product/{id}")
    public String getProduct(@PathVariable("id") Long productId, Model model){
        model.addAttribute("product", productService.getProduct(productId));
        return "product";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

}
