package com.gmail.jameshealey1994.simplepvptoggle.utils;

/**
 * Utility class with some static methods and values, stored here to help keep
 * the rest of the project's code clean.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class BooleanValuesUtils {
    
    /**
     * Strings representing a positive value.
     */
    public static final String[] POSITIVE_VALUES = new String[] {"true", "t", "yes", "y", "on"};
    
    /**
     * Strings representing a negative value.
     */
    public static final String[] NEGATIVE_VALUES = new String[] {"false", "f", "no", "n", "off"};
    
    /**
     * Converts a string to a Boolean depending on the contents of the String.
     * Case insensitive.
     * Can be null.
     * 
     * @param string    string being parsed
     * @return          true or false if the passed String is equal to a value
     *                  in the positive or negative values String arrays, null
     *                  if no matches are found
     */
    public static Boolean parse(String string) {
        for (String s : POSITIVE_VALUES) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        for (String s : NEGATIVE_VALUES) {
            if (string.equalsIgnoreCase(s)) {
                return false;
            }
        }
        return null;
    }
}
