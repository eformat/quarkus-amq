package org.acme.messaging;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(methods = true, fields = true)
public class Order {

    private String product;
    private String name;
    private String orderId;

    public Order() {
        // empty
    }

    public Order(String name, String product) {
        this.name = name;
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + name + "\n");
        buffer.append("Product: " + product + "\n");
        buffer.append("OrderId: " + orderId + "\n");
        return buffer.toString();
    }
}
