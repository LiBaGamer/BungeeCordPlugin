package de.libagamer.bungeecordplugin.main.commands;

import de.libagamer.bungeecordplugin.main.Main;
import de.libagamer.bungeecordplugin.main.util.MojangAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class UnbanCommand extends Command {

    private Main instance;
    private Configuration configuration;
    private MojangAPI api = new MojangAPI();

    public UnbanCommand(String name, Main main, Configuration config) {
        super(name);
        this.instance = main;
        this.configuration = config;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(strings.length == 1){

            String targetPlayer = strings[0];

            String playerUUID = api.getUuid(targetPlayer);

            String durationpath = "bans." + playerUUID + ".duration";
            String reasonpath = "bans." + playerUUID + ".reason";
            long resettime = 1;

            configuration.set(durationpath, resettime);

            commandSender.sendMessage(new TextComponent(ChatColor.GREEN + "Spieler " + targetPlayer + " wurde entbannt!"));


        }
        else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Falsche Benutzung des Commands!"));
        }


    }
}
