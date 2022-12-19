package uk.lewisl.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.commands.cmds.KitCreate;
import uk.lewisl.kitpvp.commands.subCommands.Bypass;
import uk.lewisl.kitpvp.commands.subCommands.Setup;
import uk.lewisl.kitpvp.types.ArgumentComponent;
import uk.lewisl.kitpvp.types.CmdHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CmdManager implements CommandExecutor, TabCompleter {

    public ArrayList<CmdHandler> cmdsList = new ArrayList<>();
    private KitPvp plugin;
    private String command = "kitpvp";

    /**
     * @param plugin
     */
    public void setup(KitPvp plugin){
        this.plugin = plugin;

        plugin.getCommand(command).setTabCompleter(this);
        plugin.getCommand(command).setExecutor(this);



        this.cmdsList.add(new Bypass());
        this.cmdsList.add(new Setup());


        for (CmdHandler cmd: cmdsList) {
            cmd.setup(plugin);
        }
    }


    /**
     * @param sender Source of the command
     * @param command
     * @param string
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        //check to make sure the sender is an actual player
        if(!(sender instanceof Player)){sender.sendMessage(ChatColor.RED + "Commands can only be executed from ingame"); return true;}

        Player player = (Player)sender;

        //if there is no subcommand
        if(args.length == 0){
            //can send help command eventually, for now just send any message.
            //  player.sendMessage(ChatColor.RED + "Subcommand not provided!");
            //plugin.guiManager.onOpen(player, "mainMenu");
            player.sendMessage("Help");
            return true;
        }

        CmdHandler targetCmd = getCmd(args[0]);

        if(targetCmd == null){
            //change later, for now just send not found
            player.sendMessage(ChatColor.RED + "Subcommand not found!");
            return true;
        }

        if(!player.hasPermission(targetCmd.permission()) || !player.isOp()){
            //change later, for now just send no permissions message
            player.sendMessage(ChatColor.RED + "You do not have the correct permissions to execute this command");
            return true;
        }


        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.addAll(Arrays.asList(args));
        arrayList.remove(0); //remove command from the array.
        ArrayList<ArgumentComponent> arguments = targetCmd.arguments;


        for(int i = 0; i < arguments.size(); i++){
            ArgumentComponent arg = arguments.get(i);
            if(arg.getValue() == null && arguments.size() < arrayList.size()){
                StringBuilder stringBuilder = new StringBuilder();

                for (ArgumentComponent sn: targetCmd.arguments) {
                    stringBuilder.append("("+ sn.getName()+") ");
                }
                player.sendMessage(ChatColor.RED + "You have not provided enough arguments, "+ stringBuilder);
                return true;
            }


            if(arguments.size() == arrayList.size()){
                targetCmd.arguments.get(i).setValue(arrayList.get(i));
            }





        }





        try {
            targetCmd.onCommand(player, args, string);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param command the command to find
     * @return found command
     */
    private CmdHandler getCmd (String command) {

        Iterator<CmdHandler> commandsList = this.cmdsList.iterator();

        while (commandsList.hasNext()) {
            CmdHandler subCommand = (CmdHandler) commandsList.next();

            if (subCommand.name().equalsIgnoreCase(command)) return subCommand;

            Iterator<String> aliasCommandList = subCommand.aliases().iterator();

            while (aliasCommandList.hasNext()) {
                String alias = aliasCommandList.next();

                if (command.equalsIgnoreCase(alias)) return subCommand;

            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        List<String> cmds = new ArrayList<>();
        if(args.length == 1) {
            for (CmdHandler cmdss : cmdsList) {
                cmds.add(cmdss.name());
            }
            return cmds;
        }else{

            CmdHandler cmdHandler = getCmd(args[1]);
            if(cmdHandler == null )return null;

            if(args.length < cmdHandler.arguments.size()){
                cmds.add(cmdHandler.arguments.get(args.length).getName());
                return cmds;

            }


        }


        return cmds;
    }


}
