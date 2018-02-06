package com.over.snowshop.controllers;

import com.over.snowshop.services.BrandService;
import com.over.snowshop.services.CategoryService;
import com.over.snowshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    private CategoryService categoryService;
    private BrandService brandService;
    private ProductService productService;

    @ModelAttribute
    private void submitAttributes(Model model){
        model.addAttribute("sections", categoryService.getSections());
        model.addAttribute("brands", brandService.getMainBrands());

    }

    @RequestMapping(value = "/")
    public String home(Model model){
        model.addAttribute("latestProducts", productService.getLatestProducts());
        model.addAttribute("bestsellers", productService.getBestsellers());
        return "home";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
