package de.libagamer.bungeecordplugin.main.events;

import de.libagamer.bungeecordplugin.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import net.md_5.bungee.config.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Login implements Listener {

    private Main instance;
    private Configuration configuration;

    public Login(Main main, Configuration config){
        this.instance = main;
        this.configuration = config;
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(LoginEvent event){

        if(instance.isMaintenance()){
            event.setCancelled(true);
            event.setCancelReason(new TextComponent(ChatColor.RED + "Der Server befindet sich im Wartungsmodus!"));
        }
        else {

            String path = "bans." + event.getConnection().getUniqueId().toString();
            String reasonpath = path + ".reason";
            String durationpath = path + ".duration";
            long duration = configuration.getLong(durationpath);
            long currentmillis = System.currentTimeMillis();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(duration);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String unbandate = sdf.format(calendar.getTime());





            if(configuration.contains(path)){

                if(duration < currentmillis){
                    event.setCancelled(false);
                }
                else {

                    long difference = currentmillis-duration;



                    String reason = configuration.getString(reasonpath);
                    String connectmessage = ChatColor.RED + "Du bist noch bis zum " + ChatColor.GREEN + unbandate + ChatColor.RED + " gebannt! \n Grund: " + ChatColor.YELLOW + reason;

                    event.setCancelled(true);
                    event.setCancelReason(new TextComponent(ChatColor.RED + connectmessage));

                }




            }

            else {
                event.setCancelled(false);
            }



        }



    }








}
