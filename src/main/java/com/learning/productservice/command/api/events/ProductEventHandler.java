package com.learning.productservice.command.api.events;

import com.learning.productservice.command.api.data.Product;
import com.learning.productservice.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreateEvents events){
        Product product = new Product();
        BeanUtils.copyProperties(events, product);
        productRepository.save(product);



    }
}
