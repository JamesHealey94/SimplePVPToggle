package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.ReloadCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.StatusCommand;

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
