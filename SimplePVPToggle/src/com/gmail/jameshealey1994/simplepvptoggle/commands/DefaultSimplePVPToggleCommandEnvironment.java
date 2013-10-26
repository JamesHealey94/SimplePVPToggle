package com.gmail.jameshealey1994.simplepvptoggle.commands;

/**
 * Represents the default command environment for the SimplePVPToggle plugin.
 * 
 * @author Saul Johnson
 */
public class DefaultSimplePVPToggleCommandEnvironment extends SimplePVPToggleCommandEnvironment {
    
    /**
     * Initialises a new instance of the default command environment for the
     * SimplePVPToggle plugin.
     */
    public DefaultSimplePVPToggleCommandEnvironment() {
        super(new SimplePVPToggleCommand[] {new HelpCommand(), new ReloadCommand(), new StatusCommand()});
    }
}
