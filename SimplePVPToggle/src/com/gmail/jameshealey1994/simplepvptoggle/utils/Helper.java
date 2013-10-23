package com.gmail.jameshealey1994.simplepvptoggle.utils;

/**
 * Helper class with some static methods to help keep the rest of the project's code clean.
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class Helper {
    
    /**
     * Returns true, false, or null, depending on whether the passed String occurs in the positive and negative values or not.
     * Ignores case
     * @param string string being parsed
     * @return true or false if the passed String is equal to a value in the positive or negative values String arrays, null if no matches are found.
     */
    public static Boolean parseBoolean(String string) {
        final String[] positiveValues = new String[] {"true", "t", "yes", "y"};
        final String[] negativeValues = new String[] {"false", "f", "no", "n"};
        Boolean status = null;
        for (String s : positiveValues) {
            if (string.equalsIgnoreCase(s)) {
                status = true;
            }
        }
        for (String s : negativeValues) {
            if (string.equalsIgnoreCase(s)) {
                status = false;
            }
        }
        return status;
    }
}
