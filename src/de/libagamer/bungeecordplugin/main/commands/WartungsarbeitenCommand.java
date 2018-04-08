package de.libagamer.bungeecordplugin.main.commands;

import de.libagamer.bungeecordplugin.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class WartungsarbeitenCommand extends Command {

    private Main instance;

    public WartungsarbeitenCommand(String name, Main main) {
        super(name);
        this.instance = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(instance.isMaintenance()){
            commandSender.sendMessage(new TextComponent(ChatColor.GREEN + "Der Server befindet sich nicht mehr im Wartungsmodus!"));
            instance.setMaintenance(false);
        }
        else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Der Server befindet sich jetzt im Wartungsmodus!"));
            instance.setMaintenance(true);
        }

    }
}
