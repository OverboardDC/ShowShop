package com.over.snowshop.controllers;

import com.over.snowshop.entities.Order;
import com.over.snowshop.entities.OrderedProduct;
import com.over.snowshop.objects.Cart;
import com.over.snowshop.objects.SortingTypesMap;
import com.over.snowshop.services.CategoryService;
import com.over.snowshop.services.OrderService;
import com.over.snowshop.services.ProductService;
import com.over.snowshop.validators.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    private ProductService productService;
    private CategoryService categoryService;
    private OrderService orderService;
    private OrderValidator orderValidator;

    @ModelAttribute
    public void submitAttributes(Model model) {
        model.addAttribute("sections", categoryService.getSections());
        model.addAttribute("sortingTypes", SortingTypesMap.getMap());

    }

    @RequestMapping(value = "/cart")
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("cart", session.getAttribute("cart"));
        return "cart";
    }

    @RequestMapping(value = "/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id, HttpSession session) {
        Cart cart = Cart.getInstance(session);
        Cart.addToCart(new OrderedProduct(productService.getProduct(id), 1));
        session.setAttribute("cart", cart);
        return "redirect:/product/" + id;
    }

    @RequestMapping(value = "/removeFromCart/{id}")
    public String removeFromCart(@PathVariable("id") Long id, HttpSession session) {
        Cart cart = Cart.getInstance(session);
        Cart.removeFromCart(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/updateQuantity/{id}", method = RequestMethod.POST)
    public String updateQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        Cart.updateQuantity(id, quantity);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkout")
    public String showCheckout(Model model, HttpSession session, @ModelAttribute Order order) {
        model.addAttribute("cart", session.getAttribute("cart"));
        return "checkout";
    }

    @RequestMapping(value = "/formOrder")
    public String formOrder(Order order , BindingResult bindingResult) {
        orderValidator.validate(order, bindingResult);
        if(bindingResult.hasErrors()){
            return "checkout";
        }
        orderService.formOrder(order);
        return "redirect:/";
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
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setOrderValidator(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }
}
