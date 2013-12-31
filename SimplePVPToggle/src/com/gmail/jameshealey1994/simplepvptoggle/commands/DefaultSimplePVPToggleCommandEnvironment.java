package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.commands.command.DebugCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.InfoCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.ReloadCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.SetCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.SetServerDefaultCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.SetWorldDefaultCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.command.StatusCommand;

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