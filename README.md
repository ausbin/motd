Motd plugin
===========

This plugin allows you to change the motd (shown in the server list) from a command or the config file. Licensed under the LGPL 2.1.

Installation
------------

Download `Motd.jar` from the Downloads tab, put it in your `plugins` folder, and restart/start/reload the server.

Config
------

Configuration can be found in `plugins/Motd/config.yml`.

* `motd` is the current motd. The default is "a minecraft server", just like vanilla.
* `autosave` specifies whether or not the new motd should be flushed to the config file whenever you change it via `/setmotd`. If false, the motd is only saved to the config file when the plugin is disabled.

Commands
--------

* `/setmotd` sets the motd. For ops only by default. Example: `/setmotd john's laggy server`.
* `/motd` outputs the current motd. 
* `/reloadmotd` reloads the motd and other config from the config file. Usuable by ops only by default.
