package com.gmail.jameshealey1994.simplepvptoggle.commands;

/**
 * Represents a plugin command environment (set of plugin commands).
 *
 * @author Saul Johnson
 */
public abstract class SimplePVPToggleCommandEnvironment {

    /**
     * Commands belonging to the plugin.
     * Listed in priority order (earlier items in the array have a higher
     * priority, when it comes to aliases for example).
     */
    protected SimplePVPToggleCommand[] commands;

    /**
     * Abstract constructor used to initialise a plugin command environment
     * (set of plugin commands).
     *
     * @param commands  the set of commands usable from this environment
     */
    public SimplePVPToggleCommandEnvironment(SimplePVPToggleCommand[] commands) {
        this.commands = commands;
    }

    /**
     * Returns commands belonging to the plugin.
     *
     * @return  commands belonging to the plugin
     */
    public SimplePVPToggleCommand[] getCommands() {
        return commands;
    }
}
