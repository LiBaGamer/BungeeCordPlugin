package de.libagamer.bungeecordplugin.main.commands;

import de.libagamer.bungeecordplugin.main.Main;
import de.libagamer.bungeecordplugin.main.util.MojangAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


public class BanCommand extends Command {

    private Main instance;
    private Configuration configuration;
    private MojangAPI api = new MojangAPI();


    public BanCommand(String name, Main main, Configuration config) {
        super(name);

        this.configuration = config;
        this.instance = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(strings.length > 2){

            BungeeCord bungeeCord = BungeeCord.getInstance();

            String targetPlayer = strings[0];
            String banlength = strings[1];

            List<String> reasonlist = new ArrayList<>();

            for(int i = 2; i < strings.length; i++){
                reasonlist.add(strings[i]);
            }

            String reason = reasonlist.stream().collect(Collectors.joining(" "));

            String playerUUID = api.getUuid(targetPlayer);
            if(playerUUID.equalsIgnoreCase("invalid name")){
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Dieser Spieler scheint nicht zu existieren!"));
            }
            else {



            boolean containsUnit = false;

            for(String checkedunit: timeunits){
                if(banlength.contains(checkedunit)){
                    containsUnit = true;
                }
            }

            if(!containsUnit){
                commandSender.sendMessage(new TextComponent(ChatColor.YELLOW + banlength + ChatColor.RED + " ist eine ungültige Zeitangabe! Versuche: h, m, s, d"));
            }
            else {

                String cuttedduration = banlength.substring(0, banlength.length() - 1);
                int duration = Integer.valueOf(cuttedduration);

                long currentTimeMillis = System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(currentTimeMillis);
                calendar.add(parseDuration(banlength), duration);
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String unbandate = sdf.format(calendar.getTime());

                long configentry = calendar.getTimeInMillis();

                boolean playerisonline = false;

                for(ProxiedPlayer allplayers : BungeeCord.getInstance().getPlayers()){
                    if(allplayers.getUniqueId().toString().equalsIgnoreCase(playerUUID)){
                        playerisonline = true;
                    }
                }



                String path = "bans." + playerUUID;

                String disconnectmessage = ChatColor.RED + "Du wurdest bis zum " + ChatColor.GREEN + unbandate + ChatColor.RED + " gebannt! \n Grund: " + ChatColor.YELLOW + reason;

                if(playerisonline){
                    ProxiedPlayer player = BungeeCord.getInstance().getPlayer(targetPlayer);
                    if (player.isConnected()) {
                        player.disconnect(new TextComponent(ChatColor.RED + disconnectmessage));
                    }
                }


                configuration.set(path + ".duration", configentry);
                configuration.set(path + ".reason", reason);

                instance.saveConfig();

            }
            }


        }
        else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Falsche Benutzung des Commands! /bann <Name> <Länge> <Grund>"));
        }


    }

    private String[] timeunits = {"h", "m", "s", "d"};

    private int parseDuration(String duration){

        for(String checkedunit : timeunits){

            if(duration.endsWith(checkedunit)){
                char letter = duration.charAt(duration.lastIndexOf(checkedunit));

                switch (letter){
                    case 'h': return Calendar.HOUR;
                    case 'm': return Calendar.MINUTE;
                    case 's': return Calendar.SECOND;
                    case 'd': return Calendar.DATE;
                    default: return  Calendar.SECOND;
                }


            }

        }

        return Calendar.SECOND;


    }

}
