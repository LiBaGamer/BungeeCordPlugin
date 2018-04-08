package de.libagamer.bungeecordplugin.main.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PostLogin implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPostLogin(PostLoginEvent event){

        ProxiedPlayer p = event.getPlayer();

        BungeeCord.getInstance().broadcast(p.getDisplayName() + ChatColor.AQUA + " hat das Netzwerk betreten!");


    }




}
