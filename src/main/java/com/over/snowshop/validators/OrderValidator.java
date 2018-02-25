package com.over.snowshop.validators;

import com.over.snowshop.entities.Order;
import com.over.snowshop.objects.Cart;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Required");
        if(Cart.getCart().isEmpty()){
            errors.rejectValue("orderedProducts", "NoProductsInTheCart");
        }

        if(!order.getEmail().isEmpty() && !order.getEmail().contains("@")){
            errors.rejectValue("email", "IncorrectEmail");
        }

        if(!order.getPhone().isEmpty() && order.getPhone().length() < 10 || order.getPhone().length() > 14){
            errors.rejectValue("phone", "IncorrectPhone");
        }

        if (order.getComment().length() > 128) {
            errors.rejectValue("comment", "MaxMessageSizeIncreased");
        }
    }
}
