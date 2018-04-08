package de.libagamer.bungeecordplugin.main.commands;

import de.libagamer.bungeecordplugin.main.data.RespondCache;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MsgCommand extends Command implements TabExecutor{

    private final RespondCache cache;


    public MsgCommand(String name, RespondCache cache) {
        super(name);
        this.cache = cache;
    }


    @Override
    public void execute(CommandSender commandSender, String[] args) {

        if(args.length < 2){
            TextComponent errormessage = new TextComponent(ChatColor.RED + "Falsche Benutzung des Commands! /msg <Spieler> <Nachricht>");
            commandSender.sendMessage(errormessage);
        }
        if(args.length >= 2){

            TextComponent listtoolarge = new TextComponent(ChatColor.RED + "Es gibt mehrere Spieler mit diesem Namen!");
            TextComponent notonline = new TextComponent(ChatColor.RED + "Dieser Spieler ist nicht online!");
            String targetPlayerName = args[0];
            List<String> stringList = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
            String message = stringList.stream().collect(Collectors.joining(" "));
            List<ProxiedPlayer> proxiedPlayers = new ArrayList<>();

            for(ProxiedPlayer player : BungeeCord.getInstance().getPlayers()){
                if(player.getName().equalsIgnoreCase(targetPlayerName)){
                    proxiedPlayers.add(player);
                }
            }

            if(proxiedPlayers.size() > 1){
                commandSender.sendMessage(listtoolarge);
            }
            if(proxiedPlayers.size() < 1){
                commandSender.sendMessage(notonline);
            }
            if(proxiedPlayers.size() == 1) {

                ProxiedPlayer targetPlayer = proxiedPlayers.get(0);
                String sender = "Unknown";
                ProxiedPlayer cmdsender = null;
                if(commandSender instanceof ProxiedPlayer){
                    sender = commandSender.getName();
                    cmdsender = (ProxiedPlayer) commandSender;
                }
                if(commandSender instanceof ConsoleCommandSender){
                    sender = "SYSTEM";
                    cmdsender = null;
                }
                String identifier = ChatColor.GREEN + ChatColor.BOLD.toString() + "MSG | " + ChatColor.GRAY + sender + ChatColor.GREEN + " → " + ChatColor.GRAY + targetPlayer.getName() + ChatColor.GREEN + " » " + ChatColor.WHITE;
                TextComponent outputmessage = new TextComponent(identifier + message);
                targetPlayer.sendMessage(ChatMessageType.CHAT, outputmessage);
                cache.saveTargetPlayer(cmdsender, targetPlayer);
                commandSender.sendMessage(outputmessage);
            }

        }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {

        List<String> strings1 = new ArrayList<>();

        if(args.length == 0){

            for(ProxiedPlayer player : BungeeCord.getInstance().getPlayers()){

                if(!player.getName().equalsIgnoreCase(commandSender.getName())) {
                    strings1.add(player.getName());
                }
            }

        }
        if(args.length == 1){

            String begin = args[0];

            for(ProxiedPlayer player : BungeeCord.getInstance().getPlayers()){

                if(!player.getName().equalsIgnoreCase(commandSender.getName())) {

                    if (player.getName().startsWith(begin)) {

                        strings1.add(player.getName());

                    }
                }

            }

        }
        else {
            strings1 = new ArrayList<>();
        }



        //new ArrayList<>(Arrays.asList(args).subList(0, args.length));

        return strings1;
    }
}
