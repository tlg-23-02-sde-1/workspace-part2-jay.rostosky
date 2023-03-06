package com.javatunes.billing;

public class TaxCalculatorFactory {

    // prevent instantiation - this is an "all-static" class
    private TaxCalculatorFactory() {
    }

    /*
     * OPTIONAL CHALLENGE:
     * Implement a cache of TaxCalculator objects, such that
     * we return a previously-created one if one is in there,
     * and if not, we create a new one, put it in the map, and then return it.
     *
     * Hint: you can use a Map<Location,TaxCalculator> for the cache
     *
     * Location   |   TaxCalculator
     * ----------------------------
     * EUROPE         EuropeTax object
     */
    public static TaxCalculator getTaxCalculator(Location location) {
        TaxCalculator calc = null;

        switch (location) {
            case ONLINE:
                calc = new OnlineTax();
                break;
            case USA:
                calc = new USATax();
                break;
            case EUROPE:
                calc = new EuropeTax();
        }
        return calc;
    }
}