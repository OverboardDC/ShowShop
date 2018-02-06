package com.over.snowshop.controllers;

import com.over.snowshop.services.BrandService;
import com.over.snowshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    private ProductService productService;
    private BrandService brandService;

    @RequestMapping(value = "/showMainImage")
    public void showMainImage(@RequestParam("id") Long productId, HttpServletResponse response) throws IOException {
        byte[] image = productService.getProductImage(productId);
        writeImage(image, response);
    }

    @RequestMapping(value = "/showBrandLogo")
    public void showBrnadImage(@RequestParam("id") Long brandId, HttpServletResponse response) throws IOException {
        byte[] image = brandService.getBrandLogo(brandId);
        writeImage(image, response);
    }

    private void writeImage(byte[] image, HttpServletResponse response) throws IOException {
        if (image != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            try {
                response.setContentLength(image.length);
                response.getOutputStream().write(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                response.getOutputStream().close();
            }
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }
}
