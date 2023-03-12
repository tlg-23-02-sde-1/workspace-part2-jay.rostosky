package com.duckrace;

import java.util.HashMap;
import java.util.Map;

public enum Reward {
    PRIZES,
    DEBIT_CARD;

    private static final Map<String,Reward> optionMap = Map.of(
            "D", DEBIT_CARD,
            "P", PRIZES
    );

    // Just fetch it from the Map and return it - easy!
    public static Reward get(String option) {
        return optionMap.get(option);
    }

    /*
     * Alternatively, don't use Map.of() and just use a HashMap<String,Reward>.
     * Then populate it here.
     *
     * Recall that a static initializer block executes when the .class file is loaded.
     * And also recall that the instances of an enum type are created at this time, too.
     */

    /*
    static {
        optionMap.put("D", DEBIT_CARD);
        optionMap.put("P", PRIZES);
    }
    */
}