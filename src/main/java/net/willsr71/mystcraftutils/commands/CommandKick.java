package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick {
    private MystcraftUtils plugin;

    public CommandKick(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(!cs.hasPermission("mystcraftutils.kick")){
            cs.sendMessage(plugin.chatUtils.getString("noPermission"));
            return;
        }
        if(args.length < 1){
            cs.sendMessage(plugin.chatUtils.parse("/myst kick <player>"));
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);
        String dimension = player.getWorld().getName();

        plugin.playerManager.sendToSpawn(player);

        plugin.commandDispatcher.sendFromConfig("kick.commands", player.getName(), dimension);

        cs.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("kick.messages.toSender"), player.getName()), dimension));
        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("kick.messages.toReceiver"), dimension));
    }
}
