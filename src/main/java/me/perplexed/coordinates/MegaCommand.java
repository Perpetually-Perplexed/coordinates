package me.perplexed.coordinates;

import me.perplexed.coordinates.utils.Coordinate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MegaCommand implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player pl)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (!Main.getInstance().leoLychee && !pl.hasPermission("coords.use")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission ton this command!");
            return true;
        }

        if (args.length == 0) {
            pl.chat("/coords list");
            return true;
        }

        switch (args[0])  {
            case "list" -> {
                for (Coordinate coord : Main.getInstance().getCoords()) {
                    pl.sendMessage(coord.getDisplayName());
                }

                return true;
            }

            case "add" -> {
                if (args.length <= 1) {
                    pl.sendMessage(ChatColor.RED + "You have to provide a name for the coordinate!");
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]);
                    sb.append(" ");
                }

                if (Main.getInstance().getCoords().stream().filter(c -> c.getDisplayName().equalsIgnoreCase(sb.toString())).toList().size() != 0) {
                    pl.sendMessage(ChatColor.RED + "A coordinate is already given this name!");
                    return true;
                }

                new Coordinate(pl.getLocation(),sb.toString());
            }

            case "remove" -> {
                if (args.length <= 1) {
                    pl.sendMessage(ChatColor.RED + "You have to provide a name for the coordinate!");
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]);
                    sb.append(" ");
                }

                if (Main.getInstance().getCoords().stream().filter(c -> c.getDisplayName().equalsIgnoreCase(sb.toString())).toList().size() == 0) {
                    pl.sendMessage(ChatColor.RED + "No such coordinate exists!");
                    return true;
                }

                // oh is this code messy? cry about it
                Main.getInstance().getCoords().remove(Main.getInstance().getCoords().stream().filter(c -> c.getDisplayName().equalsIgnoreCase(sb.toString())).toList().get(0));
            }

            case "private" -> {

            }

            case "get" -> {

            }

            default -> {
                sender.sendMessage(ChatColor.RED +  "That argument doesn't exist!");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<>();
        if (!Main.getInstance().leoLychee || !commandSender.hasPermission("coords.use"))
            return tab;
        tab.add("list"); tab.add("get"); tab.add("remove"); tab.add("add"); tab.add("private");

        if (args.length == 1) {
            List<String> shortend = new ArrayList<>();
            for (String arg : tab) {
                if (arg.toLowerCase().startsWith(args[0])) {
                    shortend.add(arg);
                }


            }
            return shortend;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            // todo do this :D
        }

        return tab;
    }
}
