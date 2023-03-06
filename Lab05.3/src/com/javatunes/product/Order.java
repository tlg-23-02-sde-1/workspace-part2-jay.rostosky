/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.product;

import com.javatunes.billing.Location;
import com.javatunes.billing.TaxCalculator;
import com.javatunes.billing.TaxCalculatorFactory;
import java.util.Collection;

public class Order {
    private final String id;
    private double cartTotal;
    private final Location location;

    public Order(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    /*
     * 0. Don't delegate this work at all, just put all 3 algorithms in here (ugh).
     *
     *    switch (location) {
     *       case USA:
     *          // paragraph of code to compute the tax amount
     *          // better: make a call to a private method to do it
     *          break;
     *       case EUROPE:
     *          // paragraph of code to compute the tax amount
     *          // better: make a call to a private method to do it
     *          break;
     *       ...
     *    }
     *
     * 1. Use a delegate object, but instantiate it directly via "new".
     *
     *    TaxCalculator calc = null;
     *    switch (location) {
     *       case USA:
     *          calc = new USATax();
     *          break;
     *       case EUROPE:
     *          calc = new EuropeTax();
     *          break;
     *       ...
     *    }
     *    return calc.taxAmount(getCartTotal());
     *
     * 2. Fetch from factory, passing the location as the "indicator."
     *    See our implementation below.
     */
    public double getTax() {
        // fetch the appropriate TaxCalculator, based on Location
        TaxCalculator calc = TaxCalculatorFactory.getTaxCalculator(getLocation());

        // delegate to the returned TaxCalculator to get the tax
        return calc.taxAmount(getCartTotal());
    }

    /**
     * DONE:
     * get the items from the cart and iterate over them, print each item's product code
     * get cart total and print
     */
    public void processCart(ShoppingCart<? extends Product> cart) {
        System.out.println("Order: " + getId() + " contains the following:");

        Collection<? extends Product> cartItems = cart.allItems();
        for (Product product : cartItems) {
            System.out.println(product.getCode());
        }

        this.cartTotal = cart.total();
        System.out.println("Order Total: " + getCartTotal());
    }

    public String getId() {
        return id;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public Location getLocation() {
        return location;
    }
}