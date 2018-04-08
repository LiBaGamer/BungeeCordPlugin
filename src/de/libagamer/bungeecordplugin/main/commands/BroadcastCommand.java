package de.libagamer.bungeecordplugin.main.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BroadcastCommand extends Command {

    private String prefix = "§8[§cBroadcast§8] ";


    public BroadcastCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {

        BungeeCord bungeeCord = BungeeCord.getInstance();

        List<String> broadcastMessage = new ArrayList<>();
        if(args.length > 0){
            broadcastMessage.addAll(Arrays.asList(args));
        }


        String output = broadcastMessage.stream().collect(Collectors.joining(" ")).replaceAll("&", "§");


        bungeeCord.broadcast("   ");
        bungeeCord.broadcast(prefix + ChatColor.RESET + output);
        bungeeCord.broadcast("   ");

    }
}
