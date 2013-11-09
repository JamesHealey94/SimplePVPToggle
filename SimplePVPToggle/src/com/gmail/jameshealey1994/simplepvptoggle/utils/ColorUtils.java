package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.ChatColor;

/**
 * Utility class to add color to strings.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class ColorUtils {

    /**
     * Adds colours to the passed string.
     *
     * @param string    string to add colours to
     * @return          passed string with colours added
     */
    public static String addColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}