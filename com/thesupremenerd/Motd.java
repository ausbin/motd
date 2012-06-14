/*
   Motd - bukkit plugin providing commands to change the motd
   Copyright (C) 2012 Austin Adams

   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with this library; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/

package com.thesupremenerd;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public class Motd extends JavaPlugin implements Listener {

    Logger log;
    String motd;
    boolean autosave;

    public void onEnable () {
        this.log = this.getLogger();
        this.loadcfg();

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable () {
        this.savecfg();
    }

    private void loadcfg () {
        FileConfiguration cfg = this.getConfig();
        cfg.options().copyDefaults(true);

        this.motd = cfg.getString("motd");
        this.autosave = cfg.getBoolean("autosave");
    }

    private void savecfg () {
        FileConfiguration cfg = this.getConfig();
        cfg.set("motd", this.motd);

        this.saveConfig();
    }

    @EventHandler
    public void pinged (ServerListPingEvent event) {
        event.setMotd(this.motd);
    }   

    // joins an array of strings into a string
    public String join (String joiner, String[] victims) {
        String joined = "";

        for (int i = 0; i < victims.length; i++) {
            boolean last = (i == (victims.length-1));

            if (last) {
                joined += victims[i];
            } else {
                joined += (victims[i] + joiner);
            }
        }
        return joined;
    }
    
    public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String name = cmd.getName();

        if (name.equalsIgnoreCase("setmotd") && args.length >= 1) {
            String newmotd = this.join(" ", args);
            
            this.motd = newmotd;

            if (this.autosave) 
                this.savecfg();

            return true;
        } else if (name.equalsIgnoreCase("motd")) {
            sender.sendMessage(this.motd);
            return true;
        } else if (name.equalsIgnoreCase("reloadmotd")) {
            this.reloadConfig();
            this.loadcfg();
            return true;
        } else {
            return false;
        }
    }
}
