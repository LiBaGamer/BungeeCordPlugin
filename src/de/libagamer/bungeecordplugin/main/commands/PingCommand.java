package de.libagamer.bungeecordplugin.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {


    public PingCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer p = (ProxiedPlayer) commandSender;
            p.sendMessage(new TextComponent("§8[§cServer§8] §7Ping: §c" + p.getPing() + "§7 ms!"));
        }
        else {
            commandSender.sendMessage(new TextComponent("Du musst ein Spieler sein!"));
        }
    }
}
