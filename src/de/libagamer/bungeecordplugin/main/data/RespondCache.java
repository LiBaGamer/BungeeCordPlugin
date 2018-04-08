package de.libagamer.bungeecordplugin.main.data;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class RespondCache {

    private HashMap<ProxiedPlayer, ProxiedPlayer> responderList = new HashMap<>();
    private HashMap<ProxiedPlayer, ProxiedPlayer> senderList = new HashMap<>();


    public void saveTargetPlayer(ProxiedPlayer sender, ProxiedPlayer reciever){

        if(sender != null){
            responderList.put(sender, reciever);
            senderList.put(reciever, sender);
        }

    }

    public ProxiedPlayer getReciever(ProxiedPlayer sender){

        ProxiedPlayer targetPlayer = null;

        if(responderList.containsKey(sender) && senderList.containsValue(sender)){
            targetPlayer = responderList.get(sender);
        }
        if(responderList.containsValue(sender) && senderList.containsKey(sender)){
            targetPlayer = senderList.get(sender);
        }

        return targetPlayer;
    }


}
