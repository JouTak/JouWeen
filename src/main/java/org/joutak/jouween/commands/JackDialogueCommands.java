package org.joutak.jouween.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.joutak.jouween.config.JouweenConst;
import org.joutak.jouween.jack.JackDialogues;

public class JackDialogueCommands extends AbstractCommand{

    public JackDialogueCommands() {
        super("jack");
    }

    @Override
    public void execute(CommandSender commandSender, Command command, String string, String[] args) {

//        JackDialogues.rootDialogue();

        if (!(commandSender instanceof Player)){
            return;
        }

        Player player = (Player) commandSender;

        if (args.length<2){
            player.sendMessage("Если ты пиздишь с Джеком, то lapitaniy чета нахуевертил с аргументами. Скрин куда и что кликнул ему в личку");
            return;
        }

        if (!args[1].equals(JouweenConst.UNIQUE_KEY)){
            player.sendMessage("иди к джеку пизди) тут уникальный ключ передается и чекается) так что можешь нахуй идти))");
            return;
        }

        JackDialogues.doDialogue(player, args[0]);

    }
}
