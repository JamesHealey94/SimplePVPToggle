package com.gmail.jameshealey1994.simplepvptoggle.commands;

/**
 * Represents the default command environment for the SimplePVPToggle plugin.
 * 
 * @author Saul Johnson
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DefaultSimplePVPToggleCommandEnvironment extends SimplePVPToggleCommandEnvironment {
    
    /**
     * Initialises a new instance of the default command environment for the
     * SimplePVPToggle plugin.
     */
    public DefaultSimplePVPToggleCommandEnvironment() {
        super(new SimplePVPToggleCommand[] {
            new HelpCommand(),
            new InfoCommand(),
            new ReloadCommand(),
            new SetCommand(),
            new SetServerDefaultCommand(),
            new SetWorldDefaultCommand(),
            new StatusCommand(),
            new DebugCommand()});
    }
}