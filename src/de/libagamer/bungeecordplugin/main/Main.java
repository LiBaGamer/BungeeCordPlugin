package de.libagamer.bungeecordplugin.main;

import de.libagamer.bungeecordplugin.main.commands.*;
import de.libagamer.bungeecordplugin.main.data.RespondCache;
import de.libagamer.bungeecordplugin.main.events.*;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
public class Main extends Plugin{

    private RespondCache cache = new RespondCache();
    private boolean maintenance;
    private Configuration configuration;

    @Override
    public void onEnable() {
        System.out.println("Plugin by LiBaGamer enabled!");

        File pluginconfig = new File(getDataFolder() + "/banns.yml");

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(!pluginconfig.exists()){
            try{
                pluginconfig.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        ConfigurationProvider configurationProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);

        try {
            configuration = configurationProvider.load(pluginconfig);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        registerCommands();
        registerEvents();


    }

    @Override
    public void onDisable() {

        System.out.println("Plugin by LiBaGamer disabled!");

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "banns.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands(){

        PluginManager pm = getProxy().getPluginManager();

        pm.registerCommand(this, new PingCommand("ping"));
        pm.registerCommand(this, new BroadcastCommand("broadcast"));
        pm.registerCommand(this, new ShutdownCommand("shutdown"));
        pm.registerCommand(this, new MsgCommand("msg", cache));
        pm.registerCommand(this, new RespondCommand("r", cache));
        pm.registerCommand(this, new WartungsarbeitenCommand("wartungsarbeiten", this));
        pm.registerCommand(this, new BanCommand("bann", this, configuration));
        pm.registerCommand(this, new UnbanCommand("unbann", this, configuration));

    }

    private void registerEvents(){

        PluginManager pm = getProxy().getPluginManager();

        pm.registerListener(this, new PostLogin());
        pm.registerListener(this, new Disconnect());
        pm.registerListener(this, new ServerSwitch());
        pm.registerListener(this, new ProxyPing(this));
        pm.registerListener(this, new Login(this, configuration));

    }

    public boolean isMaintenance(){

        return maintenance;
    }

    public void setMaintenance(boolean bool){
        maintenance = bool;
    }

    public void saveConfig(){

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "banns.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
