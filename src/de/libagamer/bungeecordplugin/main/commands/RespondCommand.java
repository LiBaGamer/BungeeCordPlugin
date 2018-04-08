package de.libagamer.bungeecordplugin.main.commands;

import de.libagamer.bungeecordplugin.main.data.RespondCache;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RespondCommand extends Command {

    private RespondCache cache;

    public RespondCommand(String name, RespondCache cache) {
        super(name);
        this.cache = cache;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {

        if(commandSender instanceof ProxiedPlayer){

            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            ProxiedPlayer targetPlayer;

            if(args.length > 0){


                if(cache.getReciever(player) != null){
                    targetPlayer = cache.getReciever(player);
                    if(targetPlayer.isConnected()){
                        List<String> stringList = new ArrayList<>(Arrays.asList(args).subList(0, args.length));
                        String message = stringList.stream().collect(Collectors.joining(" "));
                        String identifier = ChatColor.GREEN + ChatColor.BOLD.toString() + "MSG | " + ChatColor.GRAY + player + ChatColor.GREEN + " → " + ChatColor.GRAY + targetPlayer.getName() + ChatColor.GREEN + " » " + ChatColor.WHITE;
                        TextComponent outputmessage = new TextComponent(identifier + message);
                        targetPlayer.sendMessage(ChatMessageType.CHAT, outputmessage);
                        player.sendMessage(ChatMessageType.CHAT, outputmessage);
                    }
                    else {
                        player.sendMessage(new TextComponent(ChatColor.RED + "Dieser Spieler ist nicht online!"));
                    }

                }
                else {
                    player.sendMessage(new TextComponent(ChatColor.RED + "Du hast keine kürzlichen Nachrichten!"));
                }
            }
            else {
                player.sendMessage(new TextComponent(ChatColor.RED + "Der Command muss eine Nachricht enthalten! /r <Nachricht>"));
            }



        }

    }
}
