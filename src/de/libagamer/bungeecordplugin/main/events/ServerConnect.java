package de.libagamer.bungeecordplugin.main.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ServerConnect implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onServerConnected(ServerConnectedEvent event){

        BungeeCord.getInstance().broadcast(ChatColor.AQUA + "Der Server " + event.getServer().getInfo().getName() + " (" + event.getServer().getInfo().getAddress().getHostString() + ") ist nun mit dem Netzwerk verbunden!");

    }


}
