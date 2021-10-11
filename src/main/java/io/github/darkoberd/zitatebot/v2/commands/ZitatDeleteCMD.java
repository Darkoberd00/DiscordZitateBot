package io.github.darkoberd.zitatebot.v2.commands;

import io.github.darkoberd.zitatebot.v2.utils.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class ZitatDeleteCMD implements Command {
    @Override
    public String cmdname() {
        return "zitatdelete";
    }

    @Override
    public void action(SlashCommandEvent event) {
    }

    @Override
    public CommandData commandData() {
        CommandData data = new CommandData(cmdname(),help());
        data.addOption(OptionType.STRING, "id", "ID des Zitats!",true);
        return data;
    }

    @Override
    public String help() {
        return "Löscht den gewünschten Commmand.";
    }
}
