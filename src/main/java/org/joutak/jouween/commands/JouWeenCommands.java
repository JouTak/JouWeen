package org.joutak.jouween.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.type.TNT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.joutak.jouween.config.JouWeenConfig;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.Jack;
import org.joutak.jouween.jack.data.JackData;
import org.joutak.jouween.jack.files.JackFileWriter;
import org.joutak.jouween.mobs.AllMobTypes;

public class JouWeenCommands extends AbstractCommand{

    public JouWeenCommands() {
        super("jouween");
    }

    @Override
    public void execute(CommandSender commandSender, Command command, String string, String[] args) {

        if (!(commandSender instanceof Player)) {
            return;
        }

        Player playerSender = (Player) commandSender;

        if (!(playerSender.getName().equals("lapitaniy")||playerSender.getName().equals("Solus_asc"))){
            playerSender.sendMessage("ти кто? тебе низя!!!! >;(");
            return;
        }

        if (args.length==0){
            playerSender.sendMessage("чета не так с количеством аргументов");
        }

        if (args[0].equals("test")){
            test(playerSender, command, string, args);
            return;
        }

        if (args[0].equals("spawn")){
            spawnMob(playerSender.getLocation(), args);
            return;
        }

        if (args[0].equals("config")){
            config(playerSender, args);
            return;
        }

        if (args[0].equals("jack")){
            jack(playerSender, args);
            return;
        }
    }

    private void spawnMob(Location location, String[] args){
        if (args.length==1){
            AllMobTypes.spawnRandomMob(location);
            return;
        }

        AllMobTypes.getCustomMobById(Integer.parseInt(args[1])).spawnEntity(location);
    }

    private void test(Player player, Command command, String string, String[] args){
        for (int i = 0; i < 10000; i++) {
            TNTPrimed tntPrimed = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.TNT);
            tntPrimed.setFuseTicks(1);
        }
    }

    private void config(Player player, String[] args){
        if (args.length<2){
            player.sendMessage("Аргументов не хватает нихуя");
            return;
        }
        if (args[1].equals("reload")){
            new JouWeenConfig().read();
        } else {
            player.sendMessage("Не те аргументы");
        }
    }

    private void jack(Player player, String[] args){
        if (args.length<2){
            player.sendMessage("Аргументов не хватает нихуя");
            return;
        }
        if (args[1].equals("coords")){
//            JackData.x=player.getLocation().getX();
//            JackData.y=player.getLocation().getY();
//            JackData.z=player.getLocation().getZ();
            JackData.getInstance().setX(player.getLocation().getX());
            JackData.getInstance().setY(player.getLocation().getY());
            JackData.getInstance().setZ(player.getLocation().getZ());
            JackFileWriter jackFileWriter = new JackFileWriter(JouweenConst.JACK_FILEPATH);
            jackFileWriter.write(JackData.getInstance());
            Jack.create();
        }
    }
}
