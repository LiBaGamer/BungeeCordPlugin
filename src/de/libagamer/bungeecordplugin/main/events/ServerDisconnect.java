package de.libagamer.bungeecordplugin.main.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerDisconnect implements Listener {


    @EventHandler
    public void onServerDisconnect(ServerDisconnectEvent event){

        if(BungeeCord.getInstance().getServers().isEmpty()){

            

        }


    }

}
