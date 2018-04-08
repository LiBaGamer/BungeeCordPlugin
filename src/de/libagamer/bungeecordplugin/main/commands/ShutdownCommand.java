package de.libagamer.bungeecordplugin.main.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShutdownCommand extends Command {

    public ShutdownCommand(String name) {
        super(name);
    }

    @Deprecated
    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(commandSender instanceof ProxiedPlayer){

            ProxiedPlayer p = (ProxiedPlayer) commandSender;

            System.out.println(p.getName());

            if(p.getName().equals("LiBaGamer")){

                if(strings.length > 0){

                    List<String> stringList = new ArrayList<>();

                    for(int i = 0; i < strings.length; i++){
                        stringList.add(strings[i]);
                    }

                    String reason = stringList.stream().collect(Collectors.joining(" ")).replaceAll("&", "§");

                    BungeeCord.getInstance().stop(reason);
                }
                else {
                    BungeeCord.getInstance().stop("§cProxy is restarting!");
                }



            }
            else {
                p.sendMessage(ChatColor.RED + "Du kannst keine Berechtigung auf diesen Befehl!");
            }


        }
        if(commandSender instanceof ConsoleCommandSender){

            if(strings.length > 0){

                List<String> stringList = new ArrayList<>();

                for(int i = 0; i < strings.length; i++){
                    stringList.add(strings[i]);
                }

                String reason = stringList.stream().collect(Collectors.joining(" ")).replaceAll("&", "§");

                BungeeCord.getInstance().stop(reason);
            }
            else {
                BungeeCord.getInstance().stop("§cProxy is restarting!");
            }


        }

    }
}
