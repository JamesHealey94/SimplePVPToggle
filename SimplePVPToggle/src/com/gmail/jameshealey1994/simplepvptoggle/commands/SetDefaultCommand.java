package com.gmail.jameshealey1994.simplepvptoggle.commands;

/**
 * Abstract Class representing a SimplePVPToggle set default command.
 * Allows you to set the default PVP status of the server or a world
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
abstract public class SetDefaultCommand extends SimplePVPToggleCommand {
    
    /**
     * The path being edited.
     * e.g. "Server.Default", for editing the default server PVP status
     */
    protected String path;
    
    // TODO: Remove ?
//
//    /**
//     * Constructor to add aliases and permissions.
//     */
//    public SetDefaultCommand() {
//        super();
//        super.aliases.add("setdefault");
//        super.aliases.add("h");
//        
//        this.permissions.add(new Permission("spt.setdefault.*"));
//        this.permissions.add(new Permission("spt.setdefault.server"));
//        this.permissions.add(new Permission("spt.setdefault.world"));        
//    }
//    
//    @Override
//    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
//        if (sender instanceof Player) {
//            if (!sender.hasPermission("spt.reload")) {
//                sender.sendMessage(ChatColor.RED + "You need permission 'spt.reload' to use this command.");
//                return false;
//            }
//        }
//
//        sender.sendMessage(ChatColor.LIGHT_PURPLE + "------ " + plugin.getDescription().getFullName() + " ------");
//        return true;
//    }
}
