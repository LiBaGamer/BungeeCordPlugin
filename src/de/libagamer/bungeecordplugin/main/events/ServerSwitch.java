package de.libagamer.bungeecordplugin.main.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ServerSwitch implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onServerSwitch(ServerSwitchEvent event){

        ProxiedPlayer p = event.getPlayer();

        BungeeCord.getInstance().broadcast(p.getDisplayName() + ChatColor.AQUA + " spielt jetzt auf dem Server: " + p.getServer().getInfo().getName());


    }


}
