package com.learning.productservice.command.api.aggregate;

import com.learning.productservice.command.api.commands.CreateProductCommand;
import com.learning.productservice.command.api.events.ProductCreateEvents;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreateEvents productCreateEvents = new ProductCreateEvents();

        BeanUtils.copyProperties(createProductCommand,productCreateEvents);
        AggregateLifecycle.apply(productCreateEvents);
    }

    public ProductAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductCreateEvents productCreateEvents){
        this.quantity=productCreateEvents.getQuantity();
        this.productId=productCreateEvents.getProductId();
        this.price=productCreateEvents.getPrice();
        this.name=productCreateEvents.getName();

    }

}
