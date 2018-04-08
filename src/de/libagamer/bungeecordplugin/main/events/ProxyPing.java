package de.libagamer.bungeecordplugin.main.events;

import de.libagamer.bungeecordplugin.main.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPing implements Listener {

    private Main instance;

    public ProxyPing(Main main){
        this.instance = main;
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent event){

        ServerPing ping = event.getResponse();
        ServerPing.Players players = ping.getPlayers();

        if(instance.isMaintenance()){
            ping.setVersion(new ServerPing.Protocol("Wartungsarbeiten", ping.getVersion().getProtocol() - 1));
        }
        else {
            ping.setVersion(new ServerPing.Protocol("LiBaGamer", ping.getVersion().getProtocol()));
        }

        ping.setDescriptionComponent((BungeeCord.getInstance().getServers().isEmpty()) ? new TextComponent("§cL§6i§eB§aa§bG§1a§5m§de§4r§cs §bDev §7- §bServer") : new TextComponent("§cL§6i§eB§aa§bG§1a§5m§de§4r§cs §bDev §7- §bServer §7| §coffline"));
        players.setMax(players.getOnline() + 1);
        players.setOnline(players.getOnline());
        ping.setPlayers(players);
        event.setResponse(ping);
    }


}
